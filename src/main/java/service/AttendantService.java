package service;

import lombok.Data;
import model.user.Client;
import model.user.LibraryUser;
import persistence.AttendantDao;

import javax.persistence.EntityExistsException;

@Data
public class AttendantService
{
    private AttendantDao clientDAO;

    public void createClient(String username, String password) throws IllegalArgumentException
    {
        try
        {
            LibraryUser client = Client.builder().username(username).password(password).build();
            clientDAO.addClient(client);
        }
        catch (EntityExistsException exception) {throw new IllegalArgumentException("Username already exists");}
    }
}
