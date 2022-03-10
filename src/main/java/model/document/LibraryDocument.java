package model.document;

import lombok.*;
import lombok.experimental.SuperBuilder;
import model.library.Library;

import javax.persistence.*;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class LibraryDocument
{
    @Id
    protected long id;
    @ManyToOne
    protected Library library;
    protected String title;
    protected String author;
    protected int publicationYear;
    protected int nbCopies;
}
