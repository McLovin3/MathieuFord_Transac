package persistence;

import model.user.LibraryUser;

public interface LibraryDao
{
    void addClient(LibraryUser client);
}
