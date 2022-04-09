package library.model.library;

import library.model.document.LibraryDocument;
import library.model.user.Client;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Client client;
    @OneToOne
    private LibraryDocument libraryDocument;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean returned = false;
}

