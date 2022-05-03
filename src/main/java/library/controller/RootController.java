package library.controller;

import library.dto.BookDTO;
import library.dto.BorrowDTO;
import library.dto.DocumentDTO;
import library.dto.UserDTO;
import library.exception.NotEnoughCopiesException;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @Controller
@RequiredArgsConstructor
public class RootController
{
    private final AttendantService attendantService;
    private final ClientService clientService;

    @GetMapping("/")
    public String getRootRequest(Model model)
    {
        return "index";
    }

    @GetMapping("/books")
    public String getBookRequest(Model model)
    {
        model.addAttribute("books", clientService.getAllBooks());
        return "books";
    }

    @GetMapping("/clients")
    public String getClientsRequest(Model model)
    {
        model.addAttribute("clients", attendantService.getAllClients());
        return "clients";
    }

    @GetMapping("/createClient")
    public String getCreateClient(Model model)
    {
        model.addAttribute("clientDTO", new UserDTO());
        return "createClient";
    }

    @PostMapping("/createClient")
    public String postClient(@ModelAttribute UserDTO clientDTO)
    {
        attendantService.createClient(clientDTO);
        return "redirect:/";
    }

    @GetMapping("/createBook")
    public String getCreateBook(Model model)
    {
        model.addAttribute("bookDTO", new BookDTO());
        return "createBook";
    }

    @PostMapping("/createBook")
    private String postBook(@ModelAttribute DocumentDTO documentDTO)
    {
        try
        {
            attendantService.createBook(documentDTO);
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
        model.addAttribute("clients", attendantService.getAllClients());
        model.addAttribute("books", clientService.getAllBooks());
        return "borrowDocument";
    }

    @PostMapping("/borrowDocument")
    public String postBorrowDocument(@ModelAttribute BorrowDTO borrowDTO, RedirectAttributes redirectAttributes)
    {
        try
        {
            clientService.borrowDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
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
        model.addAttribute("clients", attendantService.getAllClients());
        model.addAttribute("books", clientService.getAllBooks());
        return "returnDocument";
    }

    @PostMapping("/returnDocument")
    public String postReturnDocument(@ModelAttribute BorrowDTO borrowDTO, RedirectAttributes redirectAttributes)
    {
        try
        {
            clientService.borrowReturnDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
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
        model.addAttribute("borrows", clientService.getClientBorrows(id));
        return "borrows";
    }
}
