package library.controller;

import java.util.List;

import javax.validation.Valid;

import library.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.exception.NonExistentUserException;
import library.dto.BorrowDTO;
import library.dto.DocumentDTO;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

//TODO Return created entities
//TODO Validate no same names (But they can so argue lol)

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "http://localhost:3000")
public class RestLibraryController
{
    private final int MIN_VALUE = 0;
    private final ClientService clientService;
    private final AttendantService attendantService;

    @GetMapping("/clients")
    public ResponseEntity<List<UserDTO>> getClients()
    {
        return new ResponseEntity<>(attendantService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/borrows/{id}")
    public ResponseEntity<List<BorrowDTO>> getClientBorrows(@PathVariable("id") int clientId)
    {
        return new ResponseEntity<>(clientService.getClientBorrows(clientId), HttpStatus.OK);
    }

    @PutMapping("/borrows")
    public ResponseEntity<BorrowDTO> putBorrows(@RequestBody BorrowDTO borrowDTO)
    {
        try
        {
            clientService.borrowReturnDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/borrows")
    public ResponseEntity<String> postBorrow(@RequestBody BorrowDTO borrowDTO)
    {
        try
        {
            clientService.borrowDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<UserDTO> getClient(@PathVariable("id") int clientId)
    {
        try
        {
            return new ResponseEntity<>(attendantService.getClient(clientId), HttpStatus.OK);
        }
        catch (NonExistentUserException exception)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/documents")
    public ResponseEntity<String> postDocument(@Valid @RequestBody DocumentDTO documentDTO)
    {
        try
        {
            switch (documentDTO.getDocumentType())
            {
            case "BOOK":
                if (documentDTO.getNbPages() > MIN_VALUE &&
                        documentDTO.getEditor() != null &&
                        !documentDTO.getEditor().isBlank() &&
                        documentDTO.getBookType() != null)
                    attendantService.createBook(documentDTO);
                break;
            case "DVD":
                if (documentDTO.getRuntime() > MIN_VALUE)
                    attendantService.createDVD(documentDTO);
                break;
            case "CD":
                if (documentDTO.getRuntime() > MIN_VALUE)
                    attendantService.createCD(documentDTO);
                break;
            default:
                return ResponseEntity.badRequest().build();
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<String> postClient(@Valid @RequestBody UserDTO userDTO)
    {
        attendantService.createClient(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/attendants/{id}")
    public ResponseEntity<UserDTO> getAttendant(@PathVariable("id") int attendantId)
    {
        try
        {
            return new ResponseEntity<>(attendantService.getAttendant(attendantId), HttpStatus.OK);
        }
        catch (NonExistentUserException exception)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/attendants")
    public ResponseEntity<List<UserDTO>> get()
    {
        return new ResponseEntity<>(attendantService.getAllAttendants(), HttpStatus.OK);
    }

    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDTO>> getDocuments()
    {
        return new ResponseEntity<>(clientService.getAllDocuments(), HttpStatus.OK);
    }
}
