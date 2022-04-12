package library.service;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.DocumentDTO;
import library.dto.FineDTO;
import library.exception.*;
import library.model.document.BookType;
import library.model.document.LibraryDocument;
import library.model.library.Borrow;
import library.model.library.Fine;
import library.model.user.Client;
import library.persistence.BorrowRepository;
import library.persistence.ClientRepository;
import library.persistence.FineRepository;
import library.persistence.LibraryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

//TODO Refactor

@Service
@RequiredArgsConstructor
public class ClientService
{
    private final LibraryDocumentRepository DOCUMENT_REPO;
    private final ClientRepository CLIENT_REPO;
    private final BorrowRepository BORROW_REPO;
    private final FineRepository FINE_REPO;

    public List<BookDTO> getAllBooks()
    {
        return DataConversion.booksToDTO(DOCUMENT_REPO.findAllBooks());
    }

    public List<DocumentDTO> getAllDocuments()
    {
        return DataConversion.DocumentsToDTO(DOCUMENT_REPO.findAll());
    }

    public List<DocumentDTO> searchDocumentsByTitle(String title)
    {
        return DataConversion.DocumentsToDTO(DOCUMENT_REPO.findAllByTitleIgnoreCaseContaining(title));
    }

    public List<DocumentDTO> searchDocumentsByAuthor(String author)
    {
        return DataConversion.DocumentsToDTO(DOCUMENT_REPO.findAllByAuthorIgnoreCaseContaining(author));
    }

    public List<DocumentDTO> searchDocumentsByYear(int year)
    {
        return DataConversion.DocumentsToDTO(DOCUMENT_REPO.findAllByPublicationYear(year));
    }

    public List<BookDTO> searchBooksByCategory(String category)
    {
        return DataConversion.booksToDTO(DOCUMENT_REPO.findAllBooksByCategory(BookType.getBookType(category)));
    }

    @Transactional
    public void returnDocument(long clientId, long documentId)
            throws NonExistentClientException, NonExistentDocumentException, ClientDidNotBorrowException
    {
        Optional<LibraryDocument> documentOptional = DOCUMENT_REPO.findById(documentId);
        Optional<Client> clientOptional = CLIENT_REPO.findByIdWithFines(clientId);
        manageReturnExceptions(documentOptional, clientOptional);

        Client client = clientOptional.get();
        LibraryDocument document = documentOptional.get();

        client.setBorrows(BORROW_REPO.findAllByClientId(clientId));
        Borrow borrow = client.getBorrow(document.getId());
        borrow.setReturned(true);

        document.setNbCopies(document.getNbCopies() + 1);

        calculateFines(client, borrow);
        BORROW_REPO.save(borrow);
        DOCUMENT_REPO.save(document);
        CLIENT_REPO.save(client);
    }

    private void calculateFines(Client client, Borrow borrow)
    {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime documentReturnDate = borrow.getReturnDate();
        if (currentDate.isAfter(documentReturnDate))
        {
            Fine fine = Fine.builder()
                    .client(client)
                    .amount(DAYS.between(documentReturnDate, currentDate) * 0.25)
                    .build();

            client.addFine(fine);
            FINE_REPO.save(fine);
        }
    }

    private void manageReturnExceptions(Optional<LibraryDocument> document, Optional<Client> client)
            throws NonExistentClientException, NonExistentDocumentException
    {
        if (client.isEmpty())
            throw new NonExistentClientException();
        if (document.isEmpty())
            throw new NonExistentDocumentException();
    }

    @Transactional
    public void borrowDocument(long clientId, long documentId) throws NonExistentClientException,
            NonExistentDocumentException, ClientHasFinesException, NoMoreCopiesException
    {
        Optional<LibraryDocument> documentOptional = DOCUMENT_REPO.findById(documentId);
        Optional<Client> clientOptional = CLIENT_REPO.findByIdWithFines(clientId);
        manageBorrowDocumentExceptions(documentOptional, clientOptional);

        Client client = clientOptional.get();
        LibraryDocument document = documentOptional.get();
        client.setBorrows(BORROW_REPO.findAllByClientId(clientId));

        document.setNbCopies(document.getNbCopies() - 1);
        LocalDateTime currentTime = LocalDateTime.now();

        Borrow borrow = Borrow.builder()
                .borrowDate(currentTime)
                .document(document)
                .client(client)
                .returned(false)
                .returnDate(currentTime.plusSeconds(document.getReturnDays() * 24 * 60 * 60))
                .build();

        client.getBorrows().add(borrow);
        DOCUMENT_REPO.save(document);
        CLIENT_REPO.save(client);
        BORROW_REPO.save(borrow);
    }

    private void manageBorrowDocumentExceptions(Optional<LibraryDocument> document, Optional<Client> client)
            throws NonExistentClientException, NonExistentDocumentException, ClientHasFinesException,
            NoMoreCopiesException
    {
        if (client.isEmpty())
            throw new NonExistentClientException();

        if (client.get().hasFines())
            throw new ClientHasFinesException();

        if (document.isEmpty())
            throw new NonExistentDocumentException();

        if (document.get().getNbCopies() == 0)
            throw new NoMoreCopiesException();
    }

    public List<BorrowDTO> getClientBorrows(long clientId)
    {
        return DataConversion.borrowsToDTO(BORROW_REPO.findAllByClientId(clientId));
    }

    public List<FineDTO> getClientFines(long clientId)
    {
        return DataConversion.finesToDTO(FINE_REPO.findAllByClientId(clientId));
    }
}
