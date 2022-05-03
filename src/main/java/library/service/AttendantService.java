package library.service;

import library.dto.UserDTO;
import library.dto.DocumentDTO;
import library.exception.NonExistentUserException;
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

    public void createBook(DocumentDTO bookDTO) throws NotEnoughCopiesException
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

    public void createCD(DocumentDTO DocumentDTO)
            throws NotEnoughCopiesException
    {
        if (DocumentDTO.getNbCopies() < 1)
            throw new NotEnoughCopiesException();

        CD cd = CD.builder()
                .title(DocumentDTO.getTitle())
                .author(DocumentDTO.getAuthor())
                .publicationYear(DocumentDTO.getPublicationYear())
                .nbCopies(DocumentDTO.getNbCopies())
                .runtime(DocumentDTO.getRuntime())
                .build();

        documentRepo.save(cd);
    }

    public void createDVD(DocumentDTO documentDTO)
            throws NotEnoughCopiesException
    {
        if (documentDTO.getNbCopies() < 1)
            throw new NotEnoughCopiesException();

        DVD dvd = DVD.builder()
                .title(documentDTO.getTitle())
                .author(documentDTO.getAuthor())
                .publicationYear(documentDTO.getPublicationYear())
                .nbCopies(documentDTO.getNbCopies())
                .runtime(documentDTO.getRuntime())
                .build();

        documentRepo.save(dvd);
    }

    public UserDTO getClient(long clientId) throws NonExistentUserException
    {
        Optional<Client> clientOptional = clientRepo.findById(clientId);
        if (clientOptional.isEmpty())
            throw new NonExistentUserException();
        return DataConversion.userToDTO(clientOptional.get());
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

    public UserDTO getAttendant(long attendantId) throws NonExistentUserException
    {
        Optional<Attendant> attendantOptional = attendantRepo.findById(attendantId);
        if (attendantOptional.isEmpty())
            throw new NonExistentUserException();
        return DataConversion.userToDTO(attendantOptional.get());
    }
}
