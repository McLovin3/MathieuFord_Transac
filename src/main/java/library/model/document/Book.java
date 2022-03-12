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

    @Override
    public String toString()
    {
        return "Book{" +
                "editor='" + editor + '\'' +
                ", nbPages=" + nbPages +
                ", bookType=" + bookType +
                ", id=" + id +
                ", library=" + library.toString() +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", nbCopies=" + nbCopies +
                '}';
    }
}
