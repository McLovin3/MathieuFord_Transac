package library;

import library.dto.DocumentDTO;
import library.dto.UserDTO;
import library.service.AttendantService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MathieuFordTransacApplication implements CommandLineRunner
{
    private final AttendantService attendantService;

    public static void main(String[] args)
    {
        SpringApplication.run(MathieuFordTransacApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        attendantService.createClient(new UserDTO(0, "Mathieu", "password"));
        attendantService.createAttendant(new UserDTO(0, "Admin", "admin"));
        attendantService.createBook(new DocumentDTO(0, "20 thousand leagues under the sea", "Jules Vernes", "NOVEL",
                "Pierre-Jules Hetzel", 300, 1870, 2, "BOOK", 0));
        attendantService.createBook(new DocumentDTO(0, "Clean Code", "Robert Martin", "study", "Robert C. Martin", 200,
                2008, 2, "BOOK", 0));
    }
}
