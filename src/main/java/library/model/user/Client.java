package library.model.user;

import library.exception.ClientDidNotBorrowException;
import library.model.document.LibraryDocument;
import library.model.library.Borrow;
import library.model.library.Fine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Setter
@DiscriminatorValue("CLIENT")
public class Client extends LibraryUser
{
    @OneToMany(mappedBy = "client")
    private List<Fine> fines = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Borrow> borrows = new ArrayList<>();

    public boolean hasFines()
    {
        return !fines.isEmpty();
    }

    public Borrow getBorrow(long documentId) throws ClientDidNotBorrowException
    {
        for (Borrow borrow : borrows)
        {
            if (borrow.getLibraryDocument().getId() == documentId) return borrow;
        }
        throw new ClientDidNotBorrowException();
    }

    public void addFine(Fine fine)
    {
        fines.add(fine);
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
