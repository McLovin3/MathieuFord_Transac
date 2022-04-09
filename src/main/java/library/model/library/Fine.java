package library.model.library;

import library.model.user.Client;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Fine
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Client client;
    private double amount;

    @Override
    public String toString()
    {
        return "Fine{" + "id=" + id + ", client=" + client.getId() + ", amount=" + amount + '}';
    }
}
