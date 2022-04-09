package library.dto;

import java.util.ArrayList;
import java.util.List;

import library.model.user.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO
{
    private long id;
    private String name;
    private String password;

    public static List<ClientDTO> clientsToDTO(List<Client> clients)
    {
        List<ClientDTO> clientDTOs = new ArrayList<>();
        for (Client client : clients)
        {
            clientDTOs.add(new ClientDTO(client.getId(), client.getName(), client.getPassword()));
        }
        return clientDTOs;
    }
}
