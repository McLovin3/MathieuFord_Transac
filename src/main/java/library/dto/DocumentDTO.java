package library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO
{
    private long id;
    private String title;
    private String author;
    private String bookType;
    private String editor;
    private int nbPages;
    private int publicationYear;
    private int nbCopies;
    private String documentType;
    private int runtime;
}
