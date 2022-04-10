package library.service;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.ClientDTO;
import library.dto.DocumentDTO;
import library.dto.FineDTO;
import library.model.document.Book;
import library.model.document.CD;
import library.model.document.DVD;
import library.model.document.LibraryDocument;
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
                borrowDTOs.add(BorrowDTO.builder()
                        .id(borrow.getId())
                        .clientId(borrow.getClient().getId())
                        .documentId(borrow.getDocument().getId())
                        .borrowDate(borrow.getBorrowDate().toString())
                        .returnDate(borrow.getReturnDate().toString())
                        .returned(borrow.isReturned()).build());
            }
        }
        return borrowDTOs;
    }

    public static List<ClientDTO> clientsToDTO(List<Client> clients)
    {
        List<ClientDTO> clientDTOs = new ArrayList<>();
        for (Client client : clients)
        {

            clientDTOs.add(ClientDTO.builder()
                    .id(client.getId())
                    .name(client.getName())
                    .password(client.getPassword())
                    .build());
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
                bookDTOs.add(BookDTO.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                        .bookType(book.getBookType().toString())
                        .editor(book.getEditor())
                        .nbPages(book.getNbPages())
                        .publicationYear(book.getPublicationYear())
                        .nbCopies(book.getNbCopies())
                        .build());
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
                fineDTOS.add(FineDTO.builder()
                        .id(fine.getId())
                        .clientId(fine.getClient().getId())
                        .amount(fine.getAmount())
                        .build());
            }
        }
        return fineDTOS;
    }

    public static List<DocumentDTO> DocumentsToDTO(List<LibraryDocument> documents)
    {
        List<DocumentDTO> documentDTOs = new ArrayList<>();
        for (LibraryDocument document : documents)
        {
            if (document instanceof Book)
            {
                Book book = (Book) document;
                if (book.getBookType() != null)
                {
                    documentDTOs.add(DocumentDTO.builder()
                            .id(book.getId())
                            .title(book.getTitle())
                            .bookType(book.getBookType().toString())
                            .author(book.getAuthor())
                            .editor(book.getEditor())
                            .nbPages(book.getNbPages())
                            .publicationYear(book.getPublicationYear())
                            .nbCopies(book.getNbCopies())
                            .documentType("BOOK")
                            .build());
                }
            }

            else if (document instanceof DVD)
            {
                DVD dvd = (DVD) document;

                documentDTOs.add(DocumentDTO.builder()
                        .id(dvd.getId())
                        .title(dvd.getTitle())
                        .publicationYear(dvd.getPublicationYear())
                        .nbCopies(dvd.getNbCopies())
                        .runtime(dvd.getRuntime())
                        .documentType("DVD")
                        .build());
            }

            else if (document instanceof CD)
            {
                CD cd = (CD) document;

                documentDTOs.add(DocumentDTO.builder()
                        .id(cd.getId())
                        .title(cd.getTitle())
                        .publicationYear(cd.getPublicationYear())
                        .nbCopies(cd.getNbCopies())
                        .runtime(cd.getRuntime())
                        .documentType("CD")
                        .build());
            }
        }
        return documentDTOs;
    }
}
