package service;

import lombok.Data;
import model.document.Book;
import persistence.LibraryDao;

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
}