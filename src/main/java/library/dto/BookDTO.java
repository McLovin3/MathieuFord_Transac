package library.dto;

import java.util.ArrayList;
import java.util.List;

import library.model.document.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO
{
    private long id;
    private String title;
    private String author;
    private String bookType;
    private String editor;
    private int nbPages;
    private int publicationYear;
    private int nbCopies;

    public static List<BookDTO> BooksToDTO(List<Book> books)
    {
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books)
        {
            if (book.getBookType() != null)
            {
                bookDTOs.add(new BookDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getBookType().toString(),
                        book.getEditor(),
                        book.getNbPages(),
                        book.getPublicationYear(),
                        book.getNbCopies()));
            }
        }
        return bookDTOs;
    }
}