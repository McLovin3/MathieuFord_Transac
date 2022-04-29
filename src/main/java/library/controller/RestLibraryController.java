package library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.dto.BookDTO;
import library.dto.UserDTO;
import library.dto.DocumentDTO;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestLibraryController
{

    private final ClientService clientService;
    private final AttendantService attendantService;

    @GetMapping("/books")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public List<BookDTO> getBooks()
    {
        return clientService.getAllBooks();
    }

    @GetMapping("/clients")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public List<UserDTO> getClients()
    {
        return attendantService.getAllClients();
    }

    @GetMapping("/attendants")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public List<UserDTO> get()
    {
        return attendantService.getAllAttendants();
    }

    @GetMapping("/documents")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public List<DocumentDTO> getDocuments()
    {
        return clientService.getAllDocuments();
    }
}
