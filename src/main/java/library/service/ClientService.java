package library.service;

import library.model.document.Book;
import library.model.document.BookType;
import library.model.document.LibraryDocument;
import library.model.library.Borrow;
import library.model.library.Fine;
import library.model.user.Client;
import library.persistence.BorrowRepository;
import library.persistence.LibraryDocumentRepository;
import library.persistence.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ClientService
{

    @Autowired
    private LibraryDocumentRepository documentRepo;

    @Autowired
    private LibraryUserRepository userRepo;

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

    public void borrowDocument(long clientId, long documentId) throws IllegalArgumentException
    {
        LibraryDocument document = documentRepo.findById(documentId);
        Client client = userRepo.findClientByIdWithBorrows(clientId);

        manageBorrowDocumentExceptions(document, client);
        document.setNbCopies(document.getNbCopies() - 1);

        Borrow borrow = Borrow.builder().borrowDate(LocalDate.now()).libraryDocument(document).client(client)
                .returnDate(LocalDate.now().plusWeeks(3)).build();

        client.getBorrows().add(borrow);
        userRepo.save(client);
        borrowRepo.save(borrow);
    }

    private void manageBorrowDocumentExceptions(LibraryDocument document, Client client)
            throws IllegalArgumentException
    {
        // TODO When implemented fines, add check if client has fines.
        if (document == null)
            throw new IllegalArgumentException("Book does not exist");

        if (document.getNbCopies() == 0)
            throw new IllegalArgumentException("No copies left");
    }

    public List<Borrow> getClientBorrows(long clientId)
    {
        return userRepo.findClientByIdWithBorrows(clientId).getBorrows();
    }

    public List<Fine> getClientFines(long clientId)
    {
        return userRepo.findClientByIdWithFines(clientId).getFines();
    }
}
