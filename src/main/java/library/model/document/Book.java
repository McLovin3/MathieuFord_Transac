package library.model.document;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("BOOK")
public class Book extends LibraryDocument
{
    private String editor;
    private int nbPages;
    private BookType bookType;
}
