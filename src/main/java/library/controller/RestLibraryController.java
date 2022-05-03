package library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import library.dto.UserDTO;
import library.exception.NonExistentUserException;
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

    @GetMapping("/clients")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public ResponseEntity<List<UserDTO>> getClients()
    {
        return new ResponseEntity<>(attendantService.getAllClients(), HttpStatus.OK);
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
