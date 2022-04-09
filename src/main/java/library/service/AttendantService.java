package library.service;

import library.exception.NonExistentClientException;
import library.exception.NonExistentDocumentException;
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

    public void createClient(String name, String password)
    {
        Client client = Client.builder().name(name).password(password).build();

        CLIENT_REPO.save(client);
    }

    public void createBook(String title, String author, int publicationYear, int nbCopies, String editor, int nbPages,
            String bookType) throws IllegalArgumentException
    {
        if (nbCopies < 1)
            throw new IllegalArgumentException("Must have one or more copies");

        Book book = Book.builder().title(title).author(author).publicationYear(publicationYear).nbCopies(nbCopies)
                .editor(editor).nbPages(nbPages).bookType(library.model.document.BookType.getBookType(bookType))
                .build();

        DOCUMENT_REPO.save(book);
    }

    public void createCD(String title, String author, int publicationYear, int nbCopies, int runtime)
            throws IllegalArgumentException
    {
        if (nbCopies < 1)
            throw new IllegalArgumentException("Must have one or more copies");

        CD cd = CD.builder().title(title).author(author).publicationYear(publicationYear).nbCopies(nbCopies)
                .runtime(runtime).build();

        DOCUMENT_REPO.save(cd);
    }

    public void createDVD(String title, String author, int publicationYear, int nbCopies, int runtime)
            throws IllegalArgumentException
    {
        if (nbCopies < 1)
            throw new IllegalArgumentException("Must have one or more copies");

        DVD dvd = DVD.builder().title(title).author(author).publicationYear(publicationYear).nbCopies(nbCopies)
                .runtime(runtime).build();

        DOCUMENT_REPO.save(dvd);
    }

    public Client getClient(long clientId) throws NonExistentClientException
    {
        Optional<Client> client = CLIENT_REPO.findById(clientId);
        if (client.isEmpty())
            throw new NonExistentClientException();
        return client.get();
    }

    public LibraryDocument getDocument(long documentId) throws NonExistentDocumentException
    {
        Optional<LibraryDocument> document = DOCUMENT_REPO.findById(documentId);
        if (document.isEmpty())
            throw new NonExistentDocumentException();
        return document.get();
    }

    public List<Client> getAllClients()
    {
        return CLIENT_REPO.findAll();
    }
}
