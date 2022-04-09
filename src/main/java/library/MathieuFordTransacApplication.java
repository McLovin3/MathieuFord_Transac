package library;

import library.service.AttendantService;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class MathieuFordTransacApplication implements CommandLineRunner
{
    private final AttendantService ATTENDANT_SERVICE;

    public static void main(String[] args)
    {
        SpringApplication.run(MathieuFordTransacApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        ATTENDANT_SERVICE.createClient("Mathieu", "password");
        ATTENDANT_SERVICE.createBook("20 thousand leagues under the sea", "Jules Vernes", 1870, 2,
                "Pierre-Jules Hetzel", 300, "NOVEL");
        ATTENDANT_SERVICE.createBook("Clean Code", "Robert Martin", 2008, 1, "Robert C. martin", 200, "study");
    }
}
