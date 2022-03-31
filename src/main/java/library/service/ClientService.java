package library.service;

import library.model.library.Borrow;
import library.model.user.Client;
import lombok.Data;
import library.model.document.Book;
import library.persistence.LibraryDao;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientService
{
    private final LibraryDao LIBRARY_DAO;

    public List<Book> searchBooksByTitle(String title)
    {
        return LIBRARY_DAO.searchBooksByTitle(title);
    }

    public List<Book> searchBooksByAuthor(String author)
    {
        return LIBRARY_DAO.searchBooksByAuthor(author);
    }

    public List<Book> searchBooksByYear(String year)
    {
        return LIBRARY_DAO.searchBooksByYear(year);
    }

    public List<Book> searchBooksByCategory(String category)
    {
        return LIBRARY_DAO.searchBooksByCategory(category);
    }

    public void borrowBook(long clientId, long bookId) throws IllegalArgumentException, NoResultException
    {
        Book book = LIBRARY_DAO.getBook(bookId);
        Client client = LIBRARY_DAO.getClientWithBorrows(clientId);
        manageBorrowBookExceptions(book, client);

        book.setNbCopies(book.getNbCopies() - 1);
        Borrow borrow = Borrow.builder()
                .borrowDate(LocalDate.now())
                .libraryDocument(book)
                .client(client)
                .returnDate(LocalDate.now().plusWeeks(3))
                .build();

        client.getBorrows().add(borrow);
        LIBRARY_DAO.merge(client);
        LIBRARY_DAO.merge(book);
        LIBRARY_DAO.save(borrow);
    }

    private void manageBorrowBookExceptions(Book book, Client client) throws IllegalArgumentException
    {
        //TODO When implemented fines, add check if client has fines.
        if (book == null)
            throw new IllegalArgumentException("Book does not exist");

        if (book.getNbCopies() == 0)
            throw new IllegalArgumentException("No copies left");
    }

    public List<Borrow> getClientBorrows(long clientId)
    {
        return LIBRARY_DAO.getClientBorrows(clientId);
    }
}
