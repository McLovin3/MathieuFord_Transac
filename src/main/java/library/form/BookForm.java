package library.form;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookForm
{
    private long id;
    private String title;
    private String author;
    private String bookType;
    private String editor;
    private int nbPages;
    private int publicationYear;
    private int nbCopies;
}