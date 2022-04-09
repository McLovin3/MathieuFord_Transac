package library.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientForm 
{
    private long id;
    private String name;
    private String password;
}
