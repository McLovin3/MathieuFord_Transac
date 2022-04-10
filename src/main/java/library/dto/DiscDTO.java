package library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscDTO
{
    private long id;
    private String title;
    private String author;
    private int publicationYear;
    private int nbCopies;
    private int runtime;
}
