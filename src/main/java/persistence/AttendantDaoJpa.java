package persistence;

import model.user.LibraryUser;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AttendantDaoJpa implements AttendantDao
{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("TP1.exe");

    @Override
    public void addClient(LibraryUser client) throws EntityExistsException
    {
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(client);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
