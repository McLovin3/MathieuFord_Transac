package model.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class LibraryUser
{
    private long id;
    private String firstName;
    private String lastName;
    private String loginPassword;
}
