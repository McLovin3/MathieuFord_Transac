package library.service;

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
            borrowDTOs.add(borrowToDTO(borrow));
        }
        return borrowDTOs;
    }

    public static BorrowDTO borrowToDTO(Borrow borrow)
    {
        BorrowDTO borrowDTO = BorrowDTO.builder()
                .id(borrow.getId())
                .returned(borrow.isReturned()).build();

        if (borrow.getClient() != null
                && borrow.getDocument() != null
                && borrow.getBorrowDate() != null
                && borrow.getDocument() != null
                && borrow.getReturnDate() != null)
        {
            borrowDTO.setClientId(borrow.getClient().getId());
            borrowDTO.setDocumentId(borrow.getDocument().getId());
            borrowDTO.setDocumentName(borrow.getDocument().getTitle());
            borrowDTO.setBorrowDate(borrow.getBorrowDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            borrowDTO.setReturnDate(borrow.getReturnDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        return borrowDTO;
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

    public static List<DocumentDTO> booksToDTO(List<Book> books)
    {
        List<DocumentDTO> documentDTOs = new ArrayList<>();
        for (Book book : books)
        {
            if (book.getBookType() != null)
            {
                documentDTOs.add(bookToDTO(book));
            }
        }
        return documentDTOs;
    }

    public static DocumentDTO bookToDTO(Book book)
    {
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
        {
            documentDTO.setBookType(book.getBookType().toString());
        }
        return documentDTO;
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
                        .paid(fine.isPaid())
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
                documentDTOs.add(bookToDTO((Book) document));

            else if (document instanceof DVD)
                documentDTOs.add(dvdToDTO((DVD) document));

            else if (document instanceof CD)
                documentDTOs.add(cdToDTO((CD) document));

        }
        return documentDTOs;
    }

    public static DocumentDTO cdToDTO(CD cd)
    {
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

    public static DocumentDTO dvdToDTO(DVD dvd)
    {
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

}
