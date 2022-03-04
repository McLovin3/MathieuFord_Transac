package model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class LibraryUser
{
    private String username;
    private String password;
}
