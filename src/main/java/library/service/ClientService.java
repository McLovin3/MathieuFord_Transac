package library.service;

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
    private final LibraryDocumentRepository documentRepo;
    private final ClientRepository clientRepo;
    private final BorrowRepository borrowRepo;
    private final FineRepository fineRepo;

    public List<DocumentDTO> getAllBooks()
    {
        return DataConversion.booksToDTO(documentRepo.findAllBooks());
    }

    public List<DocumentDTO> getAllDocuments()
    {
        return DataConversion.documentsToDTO(documentRepo.findAll());
    }

    public List<DocumentDTO> searchDocumentsByTitle(String title)
    {
        return DataConversion.documentsToDTO(documentRepo.findAllByTitleIgnoreCaseContaining(title));
    }

    public List<DocumentDTO> searchDocumentsByAuthor(String author)
    {
        return DataConversion.documentsToDTO(documentRepo.findAllByAuthorIgnoreCaseContaining(author));
    }

    public List<DocumentDTO> searchDocumentsByYear(int year)
    {
        return DataConversion.documentsToDTO(documentRepo.findAllByPublicationYear(year));
    }

    public List<DocumentDTO> searchBooksByCategory(String category) throws InvalidBookTypeException
    {
        return DataConversion.booksToDTO(documentRepo.findAllBooksByCategory(BookType.getBookType(category)));
    }

    @Transactional
    public void borrowReturnDocument(long clientId, long documentId)
            throws ClientDidNotBorrowException, NonExistentUserException, NonExistentDocumentException,
            DocumentAlreadyReturnException
    {
        Client client = getClientWithFinesAndBorrows(clientId);
        LibraryDocument document = getDocument(documentId);

        Borrow borrow = client.getBorrow(document.getId());
        if (borrow.isReturned())
            throw new DocumentAlreadyReturnException();
        borrow.setReturned(true);

        document.setNbCopies(document.getNbCopies() + 1);

        calculateFines(client, borrow);
        borrowRepo.save(borrow);
        documentRepo.save(document);
        clientRepo.save(client);
    }

    private LibraryDocument getDocument(long documentId) throws NonExistentDocumentException
    {
        Optional<LibraryDocument> documentOptional = documentRepo.findById(documentId);
        if (documentOptional.isEmpty())
            throw new NonExistentDocumentException();
        return documentOptional.get();
    }

    private Client getClientWithFinesAndBorrows(long clientId) throws NonExistentUserException
    {
        Optional<Client> clientOptional = clientRepo.findByIdWithFines(clientId);

        if (clientOptional.isEmpty())
            throw new NonExistentUserException();
        Client client = clientOptional.get();

        client.setBorrows(borrowRepo.findAllByClientId(clientId));
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
            fineRepo.save(fine);
        }
    }

    @Transactional
    public BorrowDTO borrowDocument(long clientId, long documentId) throws NonExistentDocumentException,
            ClientHasFinesException, NoMoreCopiesException, NonExistentUserException,
            ClientAlreadyHasBorrowException
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
        documentRepo.save(document);
        clientRepo.save(client);
        borrowRepo.save(borrow);
        return DataConversion.borrowToDTO(borrow);
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
            if (!borrow.isReturned())
                throw new ClientAlreadyHasBorrowException();
        }
        catch (ClientDidNotBorrowException exception)
        {
        }
    }

    public List<BorrowDTO> getClientBorrows(long clientId)
    {
        return DataConversion.borrowsToDTO(borrowRepo.findAllByClientId(clientId));
    }

    public List<FineDTO> getClientFines(long clientId)
    {
        return DataConversion.finesToDTO(fineRepo.findAllByClientId(clientId));
    }

    @Transactional
    public void payClientFines(long clientId) throws NonExistentUserException
    {
        Client client = getClientWithFinesAndBorrows(clientId);

        for (Fine fine : client.getFines())
        {
            fine.setPaid(true);
            fineRepo.save(fine);
        }
    }
}
