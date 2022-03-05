package persistence;

import model.library.Borrow;

import java.util.List;

public interface ClientDao
{
    List<Borrow> getBorrows(String username);
}
