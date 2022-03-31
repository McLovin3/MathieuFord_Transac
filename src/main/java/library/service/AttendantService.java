package library.service;

import library.model.document.Book;
import library.model.document.LibraryDocument;
import library.model.user.Client;
import library.persistence.LibraryDocumentRepository;
import library.persistence.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttendantService
{
    @Autowired
    private LibraryDocumentRepository documentRepo;

    @Autowired
    private LibraryUserRepository clientRepo;


    public void createClient(String name, String password)
    {
        Client client = Client.builder().name(name).password(password).build();
        clientRepo.save(client);
    }

    public void createBook(String title, String author, int publicationYear, int nbCopies, String editor, int nbPages, String bookType) throws IllegalArgumentException
    {
        if (nbCopies < 1) throw new IllegalArgumentException("Must have one or more copies");
        Book book = Book.builder()
                .title(title)
                .author(author)
                .publicationYear(publicationYear)
                .nbCopies(nbCopies)
                .editor(editor)
                .nbPages(nbPages)
                .bookType(library.model.document.BookType.getBookType(bookType))
                .build();
        documentRepo.save(book);
    }

    public Client getClient(long clientId)
    {
        return clientRepo.findClientById(clientId);
    }

    public LibraryDocument getBook(long bookId)
    {
        return documentRepo.findById(bookId);
    }
}
