package library;

import library.service.AttendantService;
import library.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class MathieuFordTransacApplication implements CommandLineRunner
{
    @Autowired
    private AttendantService attendantService;

    @Autowired
    private ClientService clientService;

    public static void main(String[] args)
    {
        SpringApplication.run(MathieuFordTransacApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        attendantService.createClient("Mathieu", "password");
        attendantService.createBook("20 thousand leagues under the sea", "Jules Vernes", 1870, 2, "Pierre-Jules Hetzel", 300, "NOVEL");
        attendantService.createBook("Clean Code", "Robert Martin", 2008, 1, "Robert C. martin", 200, "study");

        System.out.println(clientService.searchBooksByCategory("study").get(0).toString());
        System.out.println(clientService.searchBooksByAuthor("JULES").get(0).toString());

        clientService.borrowDocument(1, 2);
        System.out.println(clientService.getClientBorrows(1).get(0).toString());
    }
}
