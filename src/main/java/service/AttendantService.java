package service;

import lombok.Data;
import model.document.Book;
import model.library.Library;
import model.user.Client;
import model.user.LibraryUser;
import persistence.LibraryDao;

@Data
public class AttendantService
{
    private final LibraryDao LIBRARY_DAO;

    public void createClient(String name, String password)
    {
        LibraryUser client = Client.builder().name(name).password(password).build();
        LIBRARY_DAO.saveClient(client);
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
                .bookType(model.document.BookType.getBookType(bookType))
                .build();
        LIBRARY_DAO.saveBook(book);
    }

    public void addClientToLibrary(long clientId, long libraryId)
    {
        Library library = getLibrary(libraryId);
        Client client = getClient(clientId);

        library.getUSERS().add(client);
        client.setLibrary(library);

        LIBRARY_DAO.merge(library);
        LIBRARY_DAO.merge(client);
    }

    public void addBookToLibrary(long bookId, long libraryId)
    {
        Library library = getLibrary(libraryId);
        Book book = getBook(bookId);

        library.getDOCUMENTS().add(book);
        book.setLibrary(library);

        LIBRARY_DAO.merge(library);
        LIBRARY_DAO.merge(book);
    }

    public void createLibrary(String name)
    {
        Library library = Library.builder().name(name).build();
        LIBRARY_DAO.saveLibrary(library);
    }

    public Library getLibrary(long libraryId)
    {
        return LIBRARY_DAO.getLibrary(libraryId);
    }

    public Client getClient(long clientId)
    {
        return LIBRARY_DAO.getClient(clientId);
    }

    public Book getBook(long bookId)
    {
        return LIBRARY_DAO.getBook(bookId);
    }
}
