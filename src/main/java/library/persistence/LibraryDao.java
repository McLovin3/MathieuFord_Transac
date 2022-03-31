package library.persistence;

import library.model.document.Book;
import library.model.library.Borrow;
import library.model.user.Client;
import library.model.user.LibraryUser;

import java.util.List;

public interface LibraryDao
{
    void saveBook(Book book);
    void saveClient(LibraryUser client);
    List<Borrow> getBorrows(String clientId);
    Client getClient(long clientId);
    Book getBook(long bookId);
    <T> void merge(T object);
    <T> void save(T object);
    List<Book> searchBooksByTitle(String title);
    List<Book> searchBooksByAuthor(String author);
    List<Book> searchBooksByYear(String year);
    List<Book> searchBooksByCategory(String category);
    List<Borrow> getClientBorrows(long clientId);
    Client getClientWithBorrows(long clientId);
}
