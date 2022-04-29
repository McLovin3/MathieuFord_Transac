package library.service;

import library.dto.BookDTO;
import library.dto.UserDTO;
import library.dto.DiscDTO;
import library.exception.NonExistentClientException;
import library.exception.NotEnoughCopiesException;
import library.model.document.Book;
import library.model.document.CD;
import library.model.document.DVD;
import library.model.user.Attendant;
import library.model.user.Client;
import library.model.user.LibraryUser;
import library.persistence.AttendantRepository;
import library.persistence.ClientRepository;
import library.persistence.LibraryDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendantService
{
    private final LibraryDocumentRepository documentRepo;
    private final ClientRepository clientRepo;
    private final AttendantRepository attendantRepo;

    public void createClient(UserDTO clientDTO)
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

    public UserDTO getClient(long clientId) throws NonExistentClientException
    {
        Optional<Client> client = clientRepo.findById(clientId);
        if (client.isEmpty())
            throw new NonExistentClientException();
        return DataConversion.userToDTO(client.get());
    }

    public List<UserDTO> getAllClients()
    {
        List<Client> clients = clientRepo.findAll();
        List<LibraryUser> users = new LinkedList<>();

        for (Client client : clients)
        {
            users.add((LibraryUser) client);
        }

        return DataConversion.usersToDTO(users);
    }

    public List<UserDTO> getAllAttendants()
    {
        List<Attendant> attendants = attendantRepo.findAll();
        List<LibraryUser> users = new LinkedList<>();

        for (Attendant attendant : attendants)
        {
            users.add((LibraryUser) attendant);
        }

        return DataConversion.usersToDTO(users);
    }
}
