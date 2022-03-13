package library.model.library;

import library.model.document.LibraryDocument;
import lombok.*;
import library.model.user.Client;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class Borrow
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Library library;
    @ManyToOne
    private Client client;
    @OneToOne
    private LibraryDocument libraryDocument;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}

