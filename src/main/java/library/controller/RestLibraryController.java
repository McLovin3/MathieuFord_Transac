package library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.dto.UserDTO;
import library.exception.NonExistentUserException;
import library.dto.BorrowDTO;
import library.dto.DocumentDTO;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

//DocumentComponenet
//TODO do I manage dates here or in backend?
//TODO error route?
//TODO redirect if invalid id in url
//TODO Do I put all backend request in app.js?

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestLibraryController
{
    private final ClientService clientService;
    private final AttendantService attendantService;

    @GetMapping("/clients")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<List<UserDTO>> getClients()
    {
        return new ResponseEntity<>(attendantService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping("/borrows/{id}")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<List<BorrowDTO>> getClientBorrows(@PathVariable("id") int clientId)
    {
        return new ResponseEntity<>(clientService.getClientBorrows(clientId), HttpStatus.OK);
    }

    @PostMapping("/borrows")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<String> getClientBorrows(@RequestBody BorrowDTO borrowDTO)
    {
        try
        {
            clientService.borrowDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            // TODO return exception message?
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/clients/{id}")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<UserDTO> getClient(@PathVariable("id") int clientId)
    {
        try
        {
            return new ResponseEntity<>(attendantService.getClient(clientId), HttpStatus.OK);
        }
        catch (NonExistentUserException exception)
        {
            // TODO return exception message?
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/documents")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<String> postDocument(@RequestBody DocumentDTO documentDTO)
    {
        try
        {
            // TODO check if fields null even if I did the form?
            switch (documentDTO.getDocumentType())
            {
            case "BOOK":
                attendantService.createBook(documentDTO);
                break;
            case "DVD":
                attendantService.createDVD(documentDTO);
                break;
            case "CD":
                attendantService.createCD(documentDTO);
                break;
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            // TODO return with created id
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            // TODO return exception message?
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/clients")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<String> postDocument(@RequestBody UserDTO userDTO)
    {
        // TODO manage cases with documents/clients with same name?
        // TODO check if fields null even if I did the form?
        attendantService.createClient(userDTO);
        // TODO return with created id
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/attendants/{id}")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<UserDTO> getAttendant(@PathVariable("id") int attendantId)
    {
        try
        {
            return new ResponseEntity<>(attendantService.getAttendant(attendantId), HttpStatus.OK);
        }
        catch (NonExistentUserException exception)
        {
            // TODO return exception message?
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/attendants")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<List<UserDTO>> get()
    {
        return new ResponseEntity<>(attendantService.getAllAttendants(), HttpStatus.OK);
    }

    @GetMapping("/documents")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<List<DocumentDTO>> getDocuments()
    {
        return new ResponseEntity<>(clientService.getAllDocuments(), HttpStatus.OK);
    }
}
