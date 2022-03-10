package service;

import lombok.Data;
import model.user.Client;
import model.user.LibraryUser;
import persistence.LibraryDao;

@Data
public class AttendantService
{
    private LibraryDao clientDAO;

    public void addClient(String username, String password)
    {
        LibraryUser client = Client.builder().username(username).password(password).build();
        clientDAO.addClient(client);
    }
}
