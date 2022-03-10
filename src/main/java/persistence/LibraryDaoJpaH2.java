package persistence;

import lombok.Data;
import model.document.Book;
import model.library.Borrow;
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

    @Override
    public void saveClient(LibraryUser client) throws EntityExistsException
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(client);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void saveBook(Book book)
    {

    }

    @Override
    public List<Borrow> getBorrows(String clientId)
    {
        return null;
    }
}
