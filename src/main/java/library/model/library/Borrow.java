package library.model.library;

import lombok.*;
import library.model.document.Book;
import library.model.user.Client;

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
    private Client client;
    @OneToOne
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}

