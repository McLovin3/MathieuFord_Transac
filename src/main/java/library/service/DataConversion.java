package library.service;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.UserDTO;
import library.dto.DocumentDTO;
import library.dto.FineDTO;
import library.model.document.Book;
import library.model.document.CD;
import library.model.document.DVD;
import library.model.document.LibraryDocument;
import library.model.library.Borrow;
import library.model.library.Fine;
import library.model.user.LibraryUser;

import java.time.format.DateTimeFormatter;
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
                        .documentName(borrow.getDocument().getTitle())
                        .documentId(borrow.getDocument().getId())
                        .borrowDate(borrow.getBorrowDate().format(DateTimeFormatter.ofPattern("dd-mm-yyyy")))
                        .returnDate(borrow.getReturnDate().format(DateTimeFormatter.ofPattern("dd-mm-yyyy")))
                        .returned(borrow.isReturned()).build());
            }
        }
        return borrowDTOs;
    }

    public static List<UserDTO> usersToDTO(List<LibraryUser> users)
    {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (LibraryUser user : users)
        {
            userDTOs.add(userToDTO(user));
        }
        return userDTOs;
    }

    public static UserDTO userToDTO(LibraryUser user)
    {
        return new UserDTO(user.getId(), user.getName(), user.getPassword());
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

    public static List<DocumentDTO> documentsToDTO(List<LibraryDocument> documents)
    {
        List<DocumentDTO> documentDTOs = new ArrayList<>();
        for (LibraryDocument document : documents)
        {
            if (document instanceof Book)
                documentDTOs.add(bookToDocumentDTO(document));

            else if (document instanceof DVD)
                documentDTOs.add(dvdToDocumentDTO(document));

            else if (document instanceof CD)
                documentDTOs.add(cdToDocumentDTO(document));

        }
        return documentDTOs;
    }

    private static DocumentDTO cdToDocumentDTO(LibraryDocument document)
    {
        CD cd = (CD) document;

        return DocumentDTO.builder()
                .id(cd.getId())
                .title(cd.getTitle())
                .author(cd.getAuthor())
                .publicationYear(cd.getPublicationYear())
                .nbCopies(cd.getNbCopies())
                .runtime(cd.getRuntime())
                .documentType("CD")
                .build();
    }

    private static DocumentDTO dvdToDocumentDTO(LibraryDocument document)
    {
        DVD dvd = (DVD) document;

        return DocumentDTO.builder()
                .id(dvd.getId())
                .title(dvd.getTitle())
                .author(dvd.getAuthor())
                .publicationYear(dvd.getPublicationYear())
                .nbCopies(dvd.getNbCopies())
                .runtime(dvd.getRuntime())
                .documentType("DVD")
                .build();
    }

    private static DocumentDTO bookToDocumentDTO(LibraryDocument document)
    {
        Book book = (Book) document;

        DocumentDTO documentDTO = DocumentDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .editor(book.getEditor())
                .nbPages(book.getNbPages())
                .publicationYear(book.getPublicationYear())
                .nbCopies(book.getNbCopies())
                .documentType("BOOK")
                .build();

        if (book.getBookType() != null)
            documentDTO.setBookType(book.getBookType().toString());

        return documentDTO;
    }
}
