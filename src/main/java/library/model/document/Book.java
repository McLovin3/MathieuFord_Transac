package library.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("BOOK")
public class Book extends LibraryDocument
{
    @Transient
    private final int RETURN_DAYS = 21;
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
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", nbCopies=" + nbCopies +
                '}';
    }

    @Override
    public int getReturnDays()
    {
        return 0;
    }
}
