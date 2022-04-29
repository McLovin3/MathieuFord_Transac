package library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.dto.BookDTO;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestLibraryController {

    private final ClientService clientService;

    @GetMapping("/books")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public List<BookDTO> getBooks() {
        return clientService.getAllBooks();
    }
}
