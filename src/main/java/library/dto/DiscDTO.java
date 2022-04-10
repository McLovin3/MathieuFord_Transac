package library.dto;

public record DiscDTO(long id,
                      String title,
                      String author,
                      int publicationYear,
                      int nbCopies,
                      int runtime)
{
}
