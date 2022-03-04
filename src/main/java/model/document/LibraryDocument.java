package model.document;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
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
    protected String title;
    protected String author;
    protected int publicationYear;
    protected boolean isTaken;
    protected LocalDateTime lastDateRented;
}
