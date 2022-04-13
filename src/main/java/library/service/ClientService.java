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
            throws ClientDidNotBorrowException, NonExistentClientException, NonExistentDocumentException
    {
        Client client = getClientWithFinesAndBorrows(clientId);
        LibraryDocument document = getDocument(documentId);

        Borrow borrow = client.getBorrow(document.getId());
        borrow.setReturned(true);

        document.setNbCopies(document.getNbCopies() + 1);

        calculateFines(client, borrow);
        BORROW_REPO.save(borrow);
        DOCUMENT_REPO.save(document);
        CLIENT_REPO.save(client);
    }

    private LibraryDocument getDocument(long documentId) throws NonExistentDocumentException
    {
        Optional<LibraryDocument> documentOptional = DOCUMENT_REPO.findById(documentId);
        if (documentOptional.isEmpty())
            throw new NonExistentDocumentException();
        return documentOptional.get();
    }

    private Client getClientWithFinesAndBorrows(long clientId) throws NonExistentClientException
    {
        Optional<Client> clientOptional = CLIENT_REPO.findByIdWithFines(clientId);

        if (clientOptional.isEmpty())
            throw new NonExistentClientException();
        Client client = clientOptional.get();

        client.setBorrows(BORROW_REPO.findAllByClientId(clientId));
        return client;
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

    @Transactional
    public void borrowDocument(long clientId, long documentId) throws 
            NonExistentDocumentException, ClientHasFinesException, NoMoreCopiesException, NonExistentClientException, ClientAlreadyHasBorrowException
    {
        Client client = getClientWithFinesAndBorrows(clientId);
        LibraryDocument document = getDocument(documentId);
        manageBorrowDocumentExceptions(document, client);

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

    private void manageBorrowDocumentExceptions(LibraryDocument document, Client client)
            throws ClientHasFinesException,
            NoMoreCopiesException, ClientAlreadyHasBorrowException
    {
        if (client.hasFines())
            throw new ClientHasFinesException();

        if (document.getNbCopies() == 0)
            throw new NoMoreCopiesException();

        try
        {
            Borrow borrow = client.getBorrow(document.getId());
            if (!borrow.isReturned()) throw new ClientAlreadyHasBorrowException();
        }
        catch (ClientDidNotBorrowException exception) {};
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
