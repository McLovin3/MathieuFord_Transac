package persistence;

import model.user.LibraryUser;

import javax.persistence.EntityExistsException;

public interface AttendantDao
{
    void addClient(LibraryUser client) throws EntityExistsException;
}
