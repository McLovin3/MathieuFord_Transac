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

        attendantService.createClient("Mathieu", "password");
        attendantService.createBook("20 thousand leagues under the sea", "Jules Vernes", 1870, 2, "Pierre-Jules Hetzel", 300, "NOVEL");
        attendantService.createBook("Clean Code", "Robert Martin", 2008, 1, "Robert C. martin", 200, "study");

        System.out.println(clientService.searchBooksByCategory("study").get(0).toString());
        System.out.println(clientService.searchBooksByAuthor("JULE").get(0).toString());

        clientService.borrowBook(1, 2);
        System.out.println(clientService.getClientBorrows(1).get(0).toString());
    }
}
