package library.model.user;

import library.model.library.Borrow;
import library.model.library.Fine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
@Entity
@DiscriminatorValue("CLIENT")
public class Client extends LibraryUser
{
    @OneToMany (mappedBy = "client")
    private List<Fine> fines = new ArrayList<>();

    @OneToMany (mappedBy = "client")
    private List<Borrow> borrows = new ArrayList<>();

    public boolean hasFines()
    {
        return !fines.isEmpty();
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
