package library.persistence;

import library.model.document.Book;
import library.model.library.Borrow;
import library.model.library.Library;
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
    void saveLibrary(Library library);
    Library getLibrary(long libraryId);
    <T> void merge(T object);
    <T> void save(T object);
    Library getLibraryWithUsers(long libraryId);
    List<Book> searchBooksByTitle(String title, long libraryId);
    List<Book> searchBooksByAuthor(String author, long libraryId);
    List<Book> searchBooksByYear(String year, long libraryId);
    List<Book> searchBooksByCategory(String category, long libraryId);
    List<Borrow> getClientBorrows(long clientId, long libraryId);
}
