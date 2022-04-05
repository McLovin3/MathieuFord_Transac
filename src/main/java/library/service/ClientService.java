package library.service;

import library.model.document.Book;
import library.model.document.BookType;
import library.model.document.LibraryDocument;
import library.model.library.Borrow;
import library.model.library.Fine;
import library.model.user.Client;
import library.persistence.BorrowRepository;
import library.persistence.ClientRepository;
import library.persistence.FineRepository;
import library.persistence.LibraryDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ClientService
{
    @Autowired
    private LibraryDocumentRepository documentRepo;

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private BorrowRepository borrowRepo;

    @Autowired
    private FineRepository fineRepo;

    public List<LibraryDocument> searchDocumentsByTitle(String title)
    {
        return documentRepo.findAllByTitleIgnoreCaseContaining(title);
    }

    public List<LibraryDocument> searchBooksByAuthor(String author)
    {
        return documentRepo.findAllByAuthorIgnoreCaseContaining(author);
    }

    public List<LibraryDocument> searchBooksByYear(int year)
    {
        return documentRepo.findAllByPublicationYear(year);
    }

    public List<Book> searchBooksByCategory(String category)
    {
        return documentRepo.findAllBooksByCategory(BookType.getBookType(category));
    }

    @Transactional
    public void returnDocument(long clientId, long documentId) throws IllegalArgumentException
    {
        LibraryDocument document = documentRepo.findById(documentId);
        Client client = clientRepo.findByIdWithBorrows(clientId);
        client.setFines(clientRepo.findByIdWithFines(clientId).getFines());

        //Possible exception return
        Borrow borrow = client.getBorrow(document);
        manageReturnExceptions(document, client);

        document.setNbCopies(document.getNbCopies() + 1);
        calculateFines(client, borrow);
        borrowRepo.deleteById(borrow.getId());
        documentRepo.save(document);
        clientRepo.save(client);
    }

    private void calculateFines(Client client, Borrow borrow)
    {
        LocalDate currentDate = LocalDate.now();
        LocalDate documentReturnDate = borrow.getReturnDate();
        if (currentDate.isAfter(documentReturnDate))
        {
            Fine fine = Fine.builder()
                    .client(client)
                    .amount(DAYS.between(documentReturnDate, currentDate) * 0.25)
                    .build();
            client.addFine(fine);
            fineRepo.save(fine);
        }
    }

    private void manageReturnExceptions(LibraryDocument document, Client client) throws IllegalArgumentException
    {
        if (client == null)
            throw new IllegalArgumentException("Client does not exist");

        if (client.hasFines())
            throw new IllegalArgumentException("Client has fines");
    }

    @Transactional
    public void borrowDocument(long clientId, long documentId) throws IllegalArgumentException
    {
        LibraryDocument document = documentRepo.findById(documentId);
        Client client = clientRepo.findByIdWithBorrows(clientId);
        client.setFines(clientRepo.findByIdWithFines(clientId).getFines());

        //Possible exception return
        manageBorrowDocumentExceptions(document, client);

        document.setNbCopies(document.getNbCopies() - 1);
        Borrow borrow = Borrow.builder()
                .borrowDate(LocalDate.now())
                .libraryDocument(document)
                .client(client)
                .returnDate(LocalDate.now().plusWeeks(3))
                .build();

        client.getBorrows().add(borrow);
        clientRepo.save(client);
        borrowRepo.save(borrow);
    }

    private void manageBorrowDocumentExceptions(LibraryDocument document, Client client)
            throws IllegalArgumentException
    {
        if (client == null)
            throw new IllegalArgumentException("Client does not exist");

        if (client.hasFines())
            throw new IllegalArgumentException("Client has fines");

        if (document == null)
            throw new IllegalArgumentException("Book does not exist");

        if (document.getNbCopies() == 0)
            throw new IllegalArgumentException("No copies left");
    }

    public List<Borrow> getClientBorrows(long clientId)
    {
        return clientRepo.findByIdWithBorrows(clientId).getBorrows();
    }

    public List<Fine> getClientFines(long clientId)
    {
        return clientRepo.findByIdWithFines(clientId).getFines();
    }
}
