package persistence;

import model.document.Book;
import model.library.Borrow;
import model.library.Library;
import model.user.Client;
import model.user.LibraryUser;

import java.util.List;

public interface LibraryDao
{
    void saveBook(Book book);
    void saveClient(LibraryUser client);
    List<Borrow> getBorrows(String clientId);
    Client getClient(long clientId);
    Book getBook(long bookId);
    void saveLibrary(Library library);
}
