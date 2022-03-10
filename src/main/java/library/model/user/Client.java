package library.model.user;

import lombok.*;
import lombok.experimental.SuperBuilder;
import library.model.library.Borrow;
import library.model.library.Fine;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client extends LibraryUser
{
    @OneToMany (mappedBy = "client")
    private List<Fine> Fines = new ArrayList<>();

    @OneToMany (mappedBy = "client")
    private List<Borrow> borrows = new ArrayList<>();
}
