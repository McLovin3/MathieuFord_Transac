package model.document;

import lombok.*;
import model.library.Library;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public abstract class LibraryDocument
{
    @Id
    protected long id;
    @ManyToOne
    protected Library library;
    protected String title;
    protected String author;
    protected int publicationYear;
    protected boolean isTaken;
    protected LocalDateTime lastDateRented;
}
