package model.library;

import lombok.*;
import model.document.Book;
import model.user.Client;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Borrow
{
    @Id
    private long id;

    @ManyToOne
    private Library library;

    @ManyToOne
    @JoinColumn(name = "client_username")
    private Client client;

    @OneToOne
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}

