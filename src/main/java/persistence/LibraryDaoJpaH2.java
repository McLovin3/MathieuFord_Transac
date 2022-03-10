package persistence;

import lombok.Data;
import model.document.Book;
import model.library.Borrow;
import model.user.Client;
import model.user.LibraryUser;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Data
public class LibraryDaoJpaH2 implements LibraryDao
{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("transac.exe");

    private <T> void save(T object)
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(object);

        entityManager.getTransaction().commit();
        entityManager.close();
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
}
