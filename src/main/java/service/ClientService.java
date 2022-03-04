package service;

import lombok.Data;
import model.user.Client;
import model.user.LibraryUser;
import persistence.ClientDAO;

@Data
public class ClientService
{
    private ClientDAO clientDAO;

    public void createClient(String username, String password) throws IllegalArgumentException
    {
        LibraryUser client = Client.builder().username(username).password(password).build();
        clientDAO.addClient(client);
    }
}
