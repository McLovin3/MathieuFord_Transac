package library.controller;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.ClientDTO;
import library.exception.NotEnoughCopiesException;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("books", CLIENT_SERVICE.getAllBooks());
        return "books";
    }

    @GetMapping("/clients")
    public String getClientsRequest(Model model)
    {
        model.addAttribute("clients", ATTENDANT_SERVICE.getAllClients());
        return "clients";
    }

    @GetMapping("/createClient")
    public String getCreateClient(Model model)
    {
        model.addAttribute("clientDTO", new ClientDTO());
        return "createClient";
    }

    @PostMapping("/createClient")
    public String postClient(@ModelAttribute ClientDTO clientDTO)
    {
        ATTENDANT_SERVICE.createClient(clientDTO);
        return "redirect:/";
    }

    @GetMapping("/createBook")
    public String getCreateBook(Model model)
    {
        model.addAttribute("bookDTO", new BookDTO());
        return "createBook";
    }

    @PostMapping("/createBook")
    private String postBook(@ModelAttribute BookDTO bookDTO)
    {
        try
        {
            ATTENDANT_SERVICE.createBook(bookDTO);
        }
        catch (NotEnoughCopiesException exception)
        {
            // HTML form validation should prevent this exception to be caught
            exception.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/borrowDocument")
    private String getBorrowDocument(Model model)
    {
        model.addAttribute("borrowDTO", new BorrowDTO());
        model.addAttribute("clients", ATTENDANT_SERVICE.getAllClients());
        model.addAttribute("books", CLIENT_SERVICE.getAllBooks());
        return "borrowDocument";
    }

    @PostMapping("/borrowDocument")
    public String postBorrowDocument(@ModelAttribute BorrowDTO borrowDTO, RedirectAttributes redirectAttributes)
    {
        try
        {
            CLIENT_SERVICE.borrowDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
        }
        catch (Exception exception)
        {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/borrowDocument";
        }
        return "redirect:/";
    }

    @GetMapping("/returnDocument")
    private String getReturnDocument(Model model)
    {
        model.addAttribute("borrowDTO", new BorrowDTO());
        model.addAttribute("clients", ATTENDANT_SERVICE.getAllClients());
        model.addAttribute("books", CLIENT_SERVICE.getAllBooks());
        return "returnDocument";
    }

    @PostMapping("/returnDocument")
    public String postReturnDocument(@ModelAttribute BorrowDTO borrowDTO, RedirectAttributes redirectAttributes)
    {
        try
        {
            CLIENT_SERVICE.returnDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
        }
        catch (Exception exception)
        {
            redirectAttributes.addFlashAttribute("errorMessage", exception.getMessage());
            return "redirect:/returnDocument";
        }
        return "redirect:/";
    }

    @GetMapping("/borrows/{id}")
    public String getBorrows(Model model, @PathVariable() Long id)
    {
        model.addAttribute("borrows", CLIENT_SERVICE.getClientBorrows(id));
        return "borrows";
    }
}
