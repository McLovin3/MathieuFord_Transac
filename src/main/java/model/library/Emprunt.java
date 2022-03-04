package model.library;

import lombok.Data;
import lombok.NoArgsConstructor;
import model.document.Book;
import model.user.Client;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Emprunt
{
    private Client client;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
