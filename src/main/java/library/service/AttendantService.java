package library.service;

import library.dto.BookDTO;
import library.dto.ClientDTO;
import library.dto.DiscDTO;
import library.exception.NonExistentClientException;
import library.exception.NotEnoughCopiesException;
import library.model.document.Book;
import library.model.document.CD;
import library.model.document.DVD;
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
    private final LibraryDocumentRepository documentRepo;
    private final ClientRepository clientRepo;

    public void createClient(ClientDTO clientDTO)
    {
        Client client = Client.builder().name(clientDTO.getName()).password(clientDTO.getPassword()).build();

        clientRepo.save(client);
    }

    public void createBook(BookDTO bookDTO) throws NotEnoughCopiesException
    {
        if (bookDTO.getNbCopies() < 1)
            throw new NotEnoughCopiesException();

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .publicationYear(bookDTO.getPublicationYear())
                .nbCopies(bookDTO.getNbCopies())
                .editor(bookDTO.getEditor())
                .nbPages(bookDTO.getNbPages())
                .bookType(library.model.document.BookType.getBookType(bookDTO.getBookType()))
                .build();

        documentRepo.save(book);
    }

    public void createCD(DiscDTO discDTO)
            throws NotEnoughCopiesException
    {
        if (discDTO.getNbCopies() < 1)
            throw new NotEnoughCopiesException();

        CD cd = CD.builder()
                .title(discDTO.getTitle())
                .author(discDTO.getAuthor())
                .publicationYear(discDTO.getPublicationYear())
                .nbCopies(discDTO.getNbCopies())
                .runtime(discDTO.getRuntime())
                .build();

        documentRepo.save(cd);
    }

    public void createDVD(DiscDTO discDTO)
            throws NotEnoughCopiesException
    {
        if (discDTO.getNbCopies() < 1)
            throw new NotEnoughCopiesException();

        DVD dvd = DVD.builder()
                .title(discDTO.getTitle())
                .author(discDTO.getAuthor())
                .publicationYear(discDTO.getPublicationYear())
                .nbCopies(discDTO.getNbCopies())
                .runtime(discDTO.getRuntime())
                .build();

        documentRepo.save(dvd);
    }

    public ClientDTO getClient(long clientId) throws NonExistentClientException
    {
        Optional<Client> client = clientRepo.findById(clientId);
        if (client.isEmpty())
            throw new NonExistentClientException();
        return DataConversion.clientToDTO(client.get());
    }

    public List<ClientDTO> getAllClients()
    {
        return DataConversion.clientsToDTO(clientRepo.findAll());
    }
}
