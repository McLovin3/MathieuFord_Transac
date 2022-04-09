package library.controller;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.ClientDTO;
import library.exception.NotEnoughCopiesException;
import library.model.library.Borrow;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RootController
{
    private final AttendantService ATTENDANT_SERVICE;
    private final ClientService CLIENT_SERVICE;

    @GetMapping("/")
    public String getRootRequest(Model model)
    {
        return "index";
    }

    @GetMapping("/books")
    public String getBookRequest(Model model)
    {
        model.addAttribute("books", BookDTO.BooksToDTO(CLIENT_SERVICE.getAllBooks()));
        return "books";
    }

    @GetMapping("/clients")
    public String getClientsRequest(Model model)
    {
        model.addAttribute("clients", ClientDTO.clientsToDTO(ATTENDANT_SERVICE.getAllClients()));
        return "clients";
    }

    @GetMapping("/createClient")
    public String getCreateClient(Model model)
    {
        model.addAttribute("clientForm", new ClientDTO());
        return "createClient";
    }

    @PostMapping("/createClient")
    public String postClient(@ModelAttribute ClientDTO clientform)
    {
        ATTENDANT_SERVICE.createClient(clientform.getName(), clientform.getPassword());
        return "redirect:/";
    }

    @GetMapping("/createBook")
    public String getCreateBook(Model model)
    {
        model.addAttribute("bookForm", new BookDTO());
        return "createBook";
    }

    @PostMapping("/createBook")
    private String postBook(@ModelAttribute BookDTO bookForm)
    {
        try
        {
            ATTENDANT_SERVICE.createBook(bookForm.getTitle(),
                    bookForm.getAuthor(),
                    bookForm.getPublicationYear(),
                    bookForm.getNbCopies(),
                    bookForm.getEditor(),
                    bookForm.getNbPages(),
                    bookForm.getBookType());
        }
        catch (NotEnoughCopiesException exception)
        {
            exception.printStackTrace();
        }
        // TODO how to manage exception?
        return "redirect:/";
    }

    @GetMapping("/borrowDocument")
    private String getBorrowDocument(Model model)
    {
        model.addAttribute("borrowForm", new BorrowDTO());
        return "borrowDocument";
    }

    @PostMapping("/borrowDocument")
    public String postBorrowDocument(@ModelAttribute BorrowDTO borrowForm)
    {
        try
        {
            CLIENT_SERVICE.borrowDocument(borrowForm.getClientId(), borrowForm.getDocumentId());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        // TODO how to manage exception?
        // TODO Why redirect?
        return "redirect:/";
    }

    @GetMapping("/returnDocument")
    private String getReturnDocument(Model model)
    {
        model.addAttribute("borrowForm", new BorrowDTO());
        return "returnDocument";
    }

    @PostMapping("/returnDocument")
    public String postReturnDocument(@ModelAttribute BorrowDTO borrowForm)
    {
        try
        {
            CLIENT_SERVICE.returnDocument(borrowForm.getClientId(), borrowForm.getDocumentId());
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
        // TODO how to manage exception?
        // TODO Why redirect?
        return "redirect:/";
    }

    @GetMapping("/borrows/{id}")
    public String getBorrows(Model model, @PathVariable(required = true) Long id)
    {
        model.addAttribute("borrows", BorrowDTO.BooksToDTO(CLIENT_SERVICE.getClientBorrows(id)));
        return "borrows";
    }
}
