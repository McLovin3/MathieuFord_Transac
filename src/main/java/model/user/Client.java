package model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import model.library.Fine;

import java.util.List;

@Data
@EqualsAndHashCode (callSuper = true)
@NoArgsConstructor
public class Client extends LibraryUser
{
    private List<Fine> Fines;
}
