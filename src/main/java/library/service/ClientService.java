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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

//TODO Manage optionals
//TODO Do I have to put date as parameter in returnDocument or is it only for testing?
//TODO instead of deleting borrow just mark it as returned
import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class ClientService
{
    private final LibraryDocumentRepository DOCUMENT_REPO;
    private final ClientRepository CLIENT_REPO;
    private final BorrowRepository BORROW_REPO;
    private final FineRepository FINE_REPO;

    public List<LibraryDocument> searchDocumentsByTitle(String title)
    {
        return DOCUMENT_REPO.findAllByTitleIgnoreCaseContaining(title);
    }

    public List<LibraryDocument> searchBooksByAuthor(String author)
    {
        return DOCUMENT_REPO.findAllByAuthorIgnoreCaseContaining(author);
    }

    public List<LibraryDocument> searchBooksByYear(int year)
    {
        return DOCUMENT_REPO.findAllByPublicationYear(year);
    }

    public List<Book> searchBooksByCategory(String category)
    {
        return DOCUMENT_REPO.findAllBooksByCategory(BookType.getBookType(category));
    }

    @Transactional
    public void returnDocument(long clientId, long documentId) throws IllegalArgumentException
    {
        LibraryDocument document = DOCUMENT_REPO.findById(documentId);
        Client client = CLIENT_REPO.findByIdWithBorrows(clientId);
        client.setFines(FINE_REPO.findAllByClient(client));

        //Possible exception return
        Borrow borrow = client.getBorrow(document);
        manageReturnExceptions(document, client);

        document.setNbCopies(document.getNbCopies() + 1);
        calculateFines(client, borrow);
        BORROW_REPO.deleteById(borrow.getId());
        DOCUMENT_REPO.save(document);
        CLIENT_REPO.save(client);
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
            FINE_REPO.save(fine);
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
        LibraryDocument document = DOCUMENT_REPO.findById(documentId);
        Client client = CLIENT_REPO.findByIdWithBorrows(clientId);
        client.setFines(CLIENT_REPO.findByIdWithFines(clientId).getFines());

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
        CLIENT_REPO.save(client);
        BORROW_REPO.save(borrow);
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
        return CLIENT_REPO.findByIdWithBorrows(clientId).getBorrows();
    }

    public List<Fine> getClientFines(long clientId)
    {
        return CLIENT_REPO.findByIdWithFines(clientId).getFines();
    }
}
