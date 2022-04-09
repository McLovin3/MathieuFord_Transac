package library.model.library;

import library.model.document.LibraryDocument;
import library.model.user.Client;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Borrow
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private Client client;
    @OneToOne
    private LibraryDocument document;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private boolean returned;
}
