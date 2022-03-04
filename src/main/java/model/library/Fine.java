package model.library;

import lombok.*;
import model.user.Client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "client_username")
    private Client client;

    private double amount;
}
