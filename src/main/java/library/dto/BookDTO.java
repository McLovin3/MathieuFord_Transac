package library.dto;

import library.model.document.Book;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    public static List<BookDTO> BooksToDTO(List<Book> books)
    {
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books)
        {
            if (book.getBookType() != null)
            {
                bookDTOs.add(new BookDTO(book.getId(),
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