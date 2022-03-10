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
}
