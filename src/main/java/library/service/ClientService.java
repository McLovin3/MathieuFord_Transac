package library.service;

import library.model.library.Borrow;
import lombok.Data;
import library.model.document.Book;
import library.persistence.LibraryDao;

import java.util.List;

@Data
public class ClientService
{
    private final LibraryDao LIBRARY_DAO;

    public List<Book> searchBooksByTitle(String title, long libraryId)
    {
        return LIBRARY_DAO.searchBooksByTitle(title, libraryId);
    }

    public List<Book> searchBooksByAuthor(String author, long libraryId)
    {
        return LIBRARY_DAO.searchBooksByAuthor(author, libraryId);
    }

    public List<Book> searchBooksByYear(String year, long libraryId)
    {
        return LIBRARY_DAO.searchBooksByYear(year, libraryId);
    }

    public List<Book> searchBooksByCategory(String category, long libraryId)
    {
        return LIBRARY_DAO.searchBooksByCategory(category, libraryId);
    }

    public void borrowBook(long clientId, long bookId)
    {

    }

    public List<Borrow> getClientBorrows(long clientId, long LibraryId)
    {
        return LIBRARY_DAO.getClientBorrows(clientId, LibraryId);
    }
}
