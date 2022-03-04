package service;

import lombok.Data;
import model.user.Client;
import model.user.LibraryUser;
import persistence.AttendantDAO;

@Data
public class AttendantService
{
    private AttendantDAO clientDAO;

    public void createClient(String username, String password)
    {
        LibraryUser client = Client.builder().username(username).password(password).build();
        clientDAO.addClient(client);
    }
}
