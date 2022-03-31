package library.persistence;

import lombok.Data;
import library.model.document.Book;
import library.model.document.BookType;
import library.model.library.Borrow;
import library.model.user.Client;
import library.model.user.LibraryUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class LibraryDaoJpaH2 implements LibraryDao
{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("transac.exe");

    @Override
    public <T> void save(T object)
    {
        EntityManager entityManager = startTransaction();
        entityManager.persist(object);
        endTransaction(entityManager);
    }

    @Override
    public List<Book> searchBooksByTitle(String title)
    {
        EntityManager entityManager = startTransaction();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE UPPER(books.title) LIKE :title", Book.class);
        query.setParameter("title", "%" + title.toUpperCase() + "%");
        List<Book> books = query.getResultList();

        endTransaction(entityManager);
        return books;
    }

    @Override
    public List<Book> searchBooksByAuthor(String author)
    {
        EntityManager entityManager = startTransaction();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE UPPER(books.author) LIKE :author", Book.class);
        query.setParameter("author", "%" + author.toUpperCase() + "%");
        List<Book> books = query.getResultList();

        endTransaction(entityManager);
        return books;
    }

    @Override
    public List<Book> searchBooksByYear(String year)
    {
        EntityManager entityManager = startTransaction();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE books.publicationYear = :year", Book.class);
        query.setParameter("year", year);
        List<Book> books = query.getResultList();

        endTransaction(entityManager);
        return books;
    }

    @Override
    public List<Book> searchBooksByCategory(String category)
    {
        BookType bookType;
        try
        {
            bookType = BookType.getBookType(category);
        }
        catch (IllegalArgumentException exception) { return new ArrayList<>(); }

        EntityManager entityManager = startTransaction();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE books.bookType = :category", Book.class);
        query.setParameter("category", bookType);
        List<Book> books = query.getResultList();

        endTransaction(entityManager);
        return books;
    }

    @Override
    public List<Borrow> getClientBorrows(long clientId)
    {
        EntityManager entityManager = startTransaction();

        final TypedQuery<Borrow> query = entityManager.createQuery(
                "SELECT borrow FROM Borrow borrow WHERE borrow.client.id = :clientId", Borrow.class);
        query.setParameter("clientId", clientId);
        List<Borrow> borrows = query.getResultList();

        endTransaction(entityManager);
        return borrows;
    }

    @Override
    public Client getClientWithBorrows(long clientId)
    {
        EntityManager entityManager = startTransaction();

        final TypedQuery<Client> query = entityManager.createQuery(
                "SELECT client FROM Client client LEFT JOIN FETCH client.borrows WHERE client.id = :clientId", Client.class);
        query.setParameter("clientId", clientId);
        Client client = query.getSingleResult();

        endTransaction(entityManager);
        return client;
    }

    @Override
    public void saveClient(LibraryUser client) throws EntityExistsException
    {
        save(client);
    }

    @Override
    public void saveBook(Book book)
    {
        save(book);
    }

    @Override
    public List<Borrow> getBorrows(String clientId)
    {
        return null;
    }

    @Override
    public Client getClient(long clientId)
    {
        EntityManager entityManager = startTransaction();

        Client client = entityManager.find(Client.class, clientId);

        endTransaction(entityManager);
        return client;
    }

    @Override
    public Book getBook(long bookId)
    {
        EntityManager entityManager = startTransaction();

        Book book = entityManager.find(Book.class, bookId);

        endTransaction(entityManager);
        return book;
    }

    @Override
    public <T> void merge(T object)
    {
        EntityManager entityManager = startTransaction();

        entityManager.merge(object);

        endTransaction(entityManager);
    }

    private EntityManager startTransaction()
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }

    private void endTransaction(EntityManager entityManager)
    {
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
