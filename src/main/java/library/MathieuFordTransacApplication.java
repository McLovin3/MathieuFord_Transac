package library;

import library.dto.BookDTO;
import library.dto.ClientDTO;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MathieuFordTransacApplication implements CommandLineRunner
{
    private final AttendantService ATTENDANT_SERVICE;
    private final ClientService CLIENT_SERVICE;

    public static void main(String[] args)
    {
        SpringApplication.run(MathieuFordTransacApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        ATTENDANT_SERVICE.createClient(new ClientDTO(0, "Mathieu", "password"));
        ATTENDANT_SERVICE.createBook(new BookDTO(0, "20 thousand leagues under the sea", "Jules Vernes", "NOVEL", "Pierre-Jules Hetzel", 300, 1870, 2));
        ATTENDANT_SERVICE.createBook(new BookDTO(0, "Clean Code", "Robert Martin", "study", "Robert C. Martin", 200, 2008,2));
        System.out.println(CLIENT_SERVICE.searchDocumentsByYear(1870).get(0).toString());
        System.out.println(CLIENT_SERVICE.getAllDocuments().get(0).toString());
        System.out.println(CLIENT_SERVICE.getAllBooks().get(0).toString());
    }
}
