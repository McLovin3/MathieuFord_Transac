package persistence;

import model.library.Borrow;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ClientDaoJpa implements ClientDao
{
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("transac.exe");

    @Override
    public List<Borrow> getBorrows(String username)
    {
        return null;
    }
}
