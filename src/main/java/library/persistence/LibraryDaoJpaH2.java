package library.persistence;

import lombok.Data;
import library.model.document.Book;
import library.model.document.BookType;
import library.model.library.Borrow;
import library.model.library.Library;
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
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(object);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Library getLibraryWithUsers(long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Library> query = entityManager.createQuery(
                "SELECT library FROM Library library LEFT JOIN FETCH library.USERS WHERE library.id = :libraryId", Library.class);
        query.setParameter("libraryId", libraryId);
        Library library = query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return library;
    }

    @Override
    public List<Book> searchBooksByTitle(String title, long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE UPPER(books.title) LIKE :title AND books.library.id = :libraryId", Book.class);
        query.setParameter("title", "%" + title.toUpperCase() + "%");
        query.setParameter("libraryId", libraryId);
        List<Book> books = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return books;
    }

    @Override
    public List<Book> searchBooksByAuthor(String author, long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE UPPER(books.author) LIKE :author AND books.library.id = :libraryId", Book.class);
        query.setParameter("author", "%" + author.toUpperCase() + "%");
        query.setParameter("libraryId", libraryId);
        List<Book> books = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return books;
    }

    @Override
    public List<Book> searchBooksByYear(String year, long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE books.publicationYear = :year AND books.library.id = :libraryId", Book.class);
        query.setParameter("year", year);
        query.setParameter("libraryId", libraryId);
        List<Book> books = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return books;
    }

    @Override
    public List<Book> searchBooksByCategory(String category, long libraryId)
    {
        BookType bookType;
        try
        {
            bookType = BookType.valueOf(category);
        }
        catch (IllegalArgumentException exception) { return new ArrayList<>(); }

        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Book> query = entityManager.createQuery(
                "SELECT books FROM Book books WHERE books.bookType = :category AND books.library.id = :libraryId", Book.class);
        query.setParameter("category", bookType);
        query.setParameter("libraryId", libraryId);
        List<Book> books = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return books;
    }

    @Override
    public List<Borrow> getClientBorrows(long clientId, long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Borrow> query = entityManager.createQuery(
                "SELECT borrow FROM Borrow borrow WHERE borrow.client.id = :clientId AND borrow.library.id = :libraryId", Borrow.class);
        query.setParameter("clientId", clientId);
        query.setParameter("libraryId", libraryId);
        List<Borrow> borrows = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return borrows;
    }

    @Override
    public Library getLibraryWithDocuments(long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Library> query = entityManager.createQuery(
                "SELECT library FROM Library library LEFT JOIN FETCH library.DOCUMENTS WHERE library.id = :libraryId", Library.class);
        query.setParameter("libraryId", libraryId);
        Library library = query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return library;
    }

    @Override
    public Library getLibraryWithBorrows(long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Library> query = entityManager.createQuery(
                "SELECT library FROM Library library LEFT JOIN FETCH library.BORROWS WHERE library.id = :libraryId", Library.class);
        query.setParameter("libraryId", libraryId);
        Library library = query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return library;
    }

    @Override
    public Client getClientWithBorrows(long clientId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        final TypedQuery<Client> query = entityManager.createQuery(
                "SELECT client FROM Client client LEFT JOIN FETCH client.borrows WHERE client.id = :clientId", Client.class);
        query.setParameter("clientId", clientId);
        Client client = query.getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

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
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Client client = entityManager.find(Client.class, clientId);

        entityManager.getTransaction().commit();
        entityManager.close();
        return client;
    }

    @Override
    public Book getBook(long bookId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Book book = entityManager.find(Book.class, bookId);

        entityManager.getTransaction().commit();
        entityManager.close();
        return book;
    }

    @Override
    public void saveLibrary(Library library)
    {
        save(library);
    }

    @Override
    public Library getLibrary(long libraryId)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Library library = entityManager.find(Library.class, libraryId);

        entityManager.getTransaction().commit();
        entityManager.close();
        return library;
    }

    @Override
    public <T> void merge(T object)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.merge(object);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
