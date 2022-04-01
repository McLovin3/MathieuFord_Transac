package library.service;

import library.model.document.Book;
import library.model.document.BookType;
import library.model.document.LibraryDocument;
import library.model.library.Borrow;
import library.model.library.Fine;
import library.model.user.Client;
import library.persistence.BorrowRepository;
import library.persistence.LibraryDocumentRepository;
import library.persistence.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class ClientService
{
    @Autowired
    private LibraryDocumentRepository documentRepo;

    @Autowired
    private ClientRepository userRepo;

    @Autowired
    private BorrowRepository borrowRepo;

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

    public void returnDocument(long clientId, long documentId) throws IllegalArgumentException
    {
        LibraryDocument document = documentRepo.findById(documentId);
        Client client = userRepo.findByIdWithBorrows(clientId);

        //Possible exception return
        Borrow borrow = client.getBorrow(document);
        manageReturnExceptions(document, client);

        document.setNbCopies(document.getNbCopies() + 1);
        calculateFines(client, borrow);
        client.getBorrows().remove(borrow);
        userRepo.save(client);
    }

    private void calculateFines(Client client, Borrow borrow)
    {
        LocalDate currentDate = LocalDate.now();
        LocalDate returnDate = borrow.getReturnDate();
        if (returnDate.isBefore(currentDate))
        {
            Fine fine = Fine.builder()
                    .client(client)
                    .amount(DAYS.between(returnDate, currentDate) * 0.25)
                    .build();
            client.getFines().add(fine);
        }
    }

    private void manageReturnExceptions(LibraryDocument document, Client client) throws IllegalArgumentException
    {
        if (client == null)
            throw new IllegalArgumentException("Client does not exist");

        if (client.hasFines())
            throw new IllegalArgumentException("Client has fines");
    }

    public void borrowDocument(long clientId, long documentId) throws IllegalArgumentException
    {
        LibraryDocument document = documentRepo.findById(documentId);
        Client client = userRepo.findByIdWithFinesAndBorrows(clientId);

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
        userRepo.save(client);
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
        return userRepo.findByIdWithBorrows(clientId).getBorrows();
    }

    public List<Fine> getClientFines(long clientId)
    {
        return userRepo.findByIdWithFines(clientId).getFines();
    }
}
