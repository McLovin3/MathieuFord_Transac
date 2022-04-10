package library.service;

import library.dto.BookDTO;
import library.dto.ClientDTO;
import library.dto.DiscDTO;
import library.exception.NonExistentClientException;
import library.exception.NonExistentDocumentException;
import library.exception.NotEnoughCopiesException;
import library.model.document.Book;
import library.model.document.CD;
import library.model.document.DVD;
import library.model.document.LibraryDocument;
import library.model.user.Client;
import library.persistence.ClientRepository;
import library.persistence.LibraryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendantService
{
    private final LibraryDocumentRepository DOCUMENT_REPO;
    private final ClientRepository CLIENT_REPO;

    public void createClient(ClientDTO clientDTO)
    {
        Client client = Client.builder().name(clientDTO.name()).password(clientDTO.password()).build();

        CLIENT_REPO.save(client);
    }

    public void createBook(BookDTO bookDTO) throws NotEnoughCopiesException
    {
        if (bookDTO.nbCopies() < 1)
            throw new NotEnoughCopiesException();

        Book book = Book.builder()
                .title(bookDTO.title())
                .author(bookDTO.author())
                .publicationYear(bookDTO.publicationYear())
                .nbCopies(bookDTO.nbCopies())
                .editor(bookDTO.editor())
                .nbPages(bookDTO.nbPages())
                .bookType(library.model.document.BookType.getBookType(bookDTO.bookType()))
                .build();

        DOCUMENT_REPO.save(book);
    }

    public void createCD(DiscDTO discDTO)
            throws NotEnoughCopiesException
    {
        if (discDTO.nbCopies() < 1)
            throw new NotEnoughCopiesException();

        CD cd = CD.builder()
                .title(discDTO.title())
                .author(discDTO.author())
                .publicationYear(discDTO.publicationYear())
                .nbCopies(discDTO.nbCopies())
                .runtime(discDTO.runtime())
                .build();

        DOCUMENT_REPO.save(cd);
    }

    public void createDVD(DiscDTO discDTO)
            throws NotEnoughCopiesException
    {
        if (discDTO.nbCopies() < 1)
            throw new NotEnoughCopiesException();

        DVD dvd = DVD.builder()
                .title(discDTO.title())
                .author(discDTO.author())
                .publicationYear(discDTO.publicationYear())
                .nbCopies(discDTO.nbCopies())
                .runtime(discDTO.runtime())
                .build();

        DOCUMENT_REPO.save(dvd);
    }

    public ClientDTO getClient(long clientId) throws NonExistentClientException
    {
        Optional<Client> client = CLIENT_REPO.findById(clientId);
        if (client.isEmpty())
            throw new NonExistentClientException();
        return DataConversion.clientToDTO(client.get());
    }

    //TODO return DTO
    public LibraryDocument getDocument(long documentId) throws NonExistentDocumentException
    {
        Optional<LibraryDocument> document = DOCUMENT_REPO.findById(documentId);
        if (document.isEmpty())
            throw new NonExistentDocumentException();
        return document.get();
    }

    public List<ClientDTO> getAllClients()
    {
        return DataConversion.clientsToDTO(CLIENT_REPO.findAll());
    }
}
