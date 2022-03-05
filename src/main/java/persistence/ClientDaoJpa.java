package persistence;

import model.library.Borrow;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClientDaoJpa implements ClientDao
{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("transac.exe");

    @Override
    public List<Borrow> getBorrows(String username)
    {
        EntityManager em = this.entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        TypedQuery<Borrow> query = em.createQuery("SELECT borrow FROM Borrow borrow WHERE borrow.client.username LIKE :usernameToSearch", Borrow.class);
        query.setParameter("usernameToSearch", "%" + username +"%");
        List<Borrow> borrows = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return borrows;
    }
}
