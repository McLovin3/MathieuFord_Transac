package library;

import library.persistence.LibraryDao;
import library.persistence.LibraryDaoJpaH2;
import library.service.AttendantService;
import library.service.ClientService;

public class Main
{
    public static void main(String[] args)
    {
        LibraryDao libraryDao = new LibraryDaoJpaH2();
        AttendantService attendantService = new AttendantService(libraryDao);
        ClientService clientService = new ClientService(libraryDao);

        attendantService.createLibrary("CAL");
        attendantService.createClient("Mathieu", "password");
        attendantService.addClientToLibrary(2, 1);
        attendantService.createBook("20 thousand leagues under the sea", "Jules Vernes", 1870, 2, "Pierre-Jules Hetzel", 300, "novel");
        attendantService.createBook("Clean Code", "Robert Martin", 2008, 1, "Robert C. martin", 200, "study");
        attendantService.addBookToLibrary(3, 1);
        attendantService.addBookToLibrary(4, 1);

        System.out.println(clientService.searchBooksByAuthor("JULE", 1).get(0).toString());

        clientService.borrowBook(2, 3, 1);
    }
}
