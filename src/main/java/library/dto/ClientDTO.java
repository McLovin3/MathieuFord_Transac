package library.dto;

import library.model.user.Client;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public record ClientDTO(long id,
                       String name,
                       String password)
{

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
