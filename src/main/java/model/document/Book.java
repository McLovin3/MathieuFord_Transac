package model.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Book extends LibraryDocument
{
    private String editor;
    private int nbPages;
    private BookType bookType;
}
