package persistence;

import model.user.LibraryUser;

public interface ClientDAO
{
    void addClient(LibraryUser client) throws IllegalArgumentException;
}
