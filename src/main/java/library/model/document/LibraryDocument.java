package library.model.document;

import lombok.*;
import lombok.experimental.SuperBuilder;
import library.model.library.Library;

import javax.persistence.*;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "documentType")
public abstract class LibraryDocument
{
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    protected long id;
    @ManyToOne
    protected Library library;
    protected String title;
    protected String author;
    protected int publicationYear;
    protected int nbCopies;
}
