package library.service;

import library.model.library.Borrow;
import library.model.library.Library;
import library.model.user.Client;
import lombok.Data;
import library.model.document.Book;
import library.persistence.LibraryDao;

import java.time.LocalDate;
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

    public void borrowBook(long clientId, long bookId, long libraryId) throws IllegalArgumentException
    {
        Client client = LIBRARY_DAO.getClientWithBorrows(clientId);
        Book book = LIBRARY_DAO.getBook(bookId);
        Library library = LIBRARY_DAO.getLibraryWithBorrows(libraryId);

        if (client == null || book == null || library == null) throw new IllegalArgumentException();
        if (client.getLibrary() == null || book.getLibrary() == null) throw new IllegalArgumentException();
        if (client.getLibrary().getId() != libraryId || book.getLibrary().getId() != libraryId) throw new IllegalArgumentException();
        if (book.getNbCopies() == 0) throw new IllegalArgumentException();

        book.setNbCopies(book.getNbCopies() - 1);
        Borrow borrow = Borrow.builder().borrowDate(LocalDate.now()).book(book).client(client).library(library).returnDate(LocalDate.now().plusWeeks(3)).build();

        client.getBorrows().add(borrow);
        library.getBORROWS().add(borrow);

        LIBRARY_DAO.merge(client);
        LIBRARY_DAO.merge(book);
        LIBRARY_DAO.merge(library);
        LIBRARY_DAO.save(borrow);
    }

    public List<Borrow> getClientBorrows(long clientId, long LibraryId)
    {
        return LIBRARY_DAO.getClientBorrows(clientId, LibraryId);
    }
}
