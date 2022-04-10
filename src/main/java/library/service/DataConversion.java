package library.service;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.ClientDTO;
import library.dto.FineDTO;
import library.model.document.Book;
import library.model.library.Borrow;
import library.model.library.Fine;
import library.model.user.Client;

import java.util.ArrayList;
import java.util.List;

class DataConversion
{
    private DataConversion()
    {
    }

    public static List<BorrowDTO> borrowsToDTO(List<Borrow> borrows)
    {
        List<BorrowDTO> borrowDTOs = new ArrayList<>();
        for (Borrow borrow : borrows)
        {
            if (borrow.getClient() != null
                    && borrow.getDocument() != null
                    && borrow.getBorrowDate() != null
                    && borrow.getDocument() != null)
            {
                borrowDTOs.add(new BorrowDTO(borrow.getId(),
                        borrow.getClient().getId(),
                        borrow.getDocument().getId(),
                        borrow.getBorrowDate().toString(),
                        borrow.getReturnDate().toString(),
                        borrow.isReturned()));
            }
        }
        return borrowDTOs;
    }

    public static List<ClientDTO> clientsToDTO(List<Client> clients)
    {
        List<ClientDTO> clientDTOs = new ArrayList<>();
        for (Client client : clients)
        {
            clientDTOs.add(new ClientDTO(client.getId(), client.getName(), client.getPassword()));
        }
        return clientDTOs;
    }

    public static ClientDTO clientToDTO(Client client)
    {
        return new ClientDTO(client.getId(), client.getName(), client.getPassword());
    }

    public static List<BookDTO> booksToDTO(List<Book> books)
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

    public static List<FineDTO> finesToDTO(List<Fine> fines)
    {
        List<FineDTO> fineDTOS = new ArrayList<>();
        for (Fine fine : fines)
        {
            if (fine.getClient() != null)
            {
                fineDTOS.add(new FineDTO(fine.getId(),
                        fine.getClient().getId(),
                        fine.getAmount()));
            }
        }
        return fineDTOS;
    }
}
