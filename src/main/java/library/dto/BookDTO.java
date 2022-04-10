package library.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public record BookDTO(long id,
                      String title,
                      String author,
                      String bookType,
                      String editor,
                      int nbPages,
                      int publicationYear,
                      int nbCopies)
{
}