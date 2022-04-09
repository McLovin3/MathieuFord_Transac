package library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDTO 
{
    private long id;
    private String name;
    private String password;
}
