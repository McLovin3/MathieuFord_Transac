package library.service;

import lombok.Data;
import library.model.document.Book;
import library.model.user.Client;
import library.model.user.LibraryUser;
import library.persistence.LibraryDao;

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
                .bookType(library.model.document.BookType.getBookType(bookType))
                .build();
        LIBRARY_DAO.saveBook(book);
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
