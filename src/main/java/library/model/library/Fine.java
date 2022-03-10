package library.model.library;

import lombok.*;
import library.model.user.Client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Fine
{
    @Id
    private long id;
    @ManyToOne
    private Client client;
    private double amount;
}
