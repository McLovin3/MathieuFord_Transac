package library.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO
{
    private long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private String bookType;
    private String editor;
    private int nbPages;
    @Min(1)
    private int publicationYear;
    @Min(1)
    private int nbCopies;
    @NotBlank
    private String documentType;
    @Min(1)
    private int runtime;
}
