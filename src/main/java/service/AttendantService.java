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

    public void createLibrary(String name)
    {
        Library library = Library.builder().name(name).build();
        LIBRARY_DAO.saveLibrary(library);
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
