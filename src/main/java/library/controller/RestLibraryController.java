package library.controller;

import java.util.List;

import javax.validation.Valid;

import library.dto.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
import library.dto.FineDTO;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;

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
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<UserDTO> putClient(@PathVariable("id") int clientId, @Validated @RequestBody UserDTO client)
    {
        try
        {
            return new ResponseEntity<>(attendantService.updateClient(client), HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/borrows")
    public ResponseEntity<BorrowDTO> postBorrow(@RequestBody BorrowDTO borrowDTO)
    {
        try
        {
            return new ResponseEntity<>(
                    clientService.borrowDocument(borrowDTO.getClientId(), borrowDTO.getDocumentId()),
                    HttpStatus.CREATED);
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
        catch (Exception exception)
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/documents")
    public ResponseEntity<DocumentDTO> postDocument(@Valid @RequestBody DocumentDTO documentDTO)
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
                    return new ResponseEntity<>(attendantService.createBook(documentDTO), HttpStatus.CREATED);
            case "DVD":
                if (documentDTO.getRuntime() > MIN_VALUE)
                    return new ResponseEntity<>(attendantService.createDVD(documentDTO), HttpStatus.CREATED);
            case "CD":
                if (documentDTO.getRuntime() > MIN_VALUE)
                    return new ResponseEntity<>(attendantService.createCD(documentDTO), HttpStatus.CREATED);
            default:
                return ResponseEntity.badRequest().build();
            }
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/clients")
    public ResponseEntity<UserDTO> postClient(@Valid @RequestBody UserDTO userDTO)
    {
        return new ResponseEntity<>(attendantService.createClient(userDTO), HttpStatus.CREATED);
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
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/fines/{id}")
    public ResponseEntity<Double> getTotalFines(@PathVariable("id") int clientId)
    {
        double fineAmount = 0;
        List<FineDTO> fines = clientService.getClientFines(clientId);

        for (FineDTO fine : fines)
        {
            if (!fine.isPaid())
            {
                fineAmount += fine.getAmount();
            }
        }

        return ResponseEntity.ok().body(fineAmount);
    }

    @PutMapping("/fines/{id}")
    public ResponseEntity<List<FineDTO>> putFines(@PathVariable("id") int clientId)
    {
        try
        {
            return new ResponseEntity<>(clientService.payClientFines(clientId), HttpStatus.CREATED);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/attendants")
    public ResponseEntity<List<UserDTO>> getFines()
    {
        return new ResponseEntity<>(attendantService.getAllAttendants(), HttpStatus.OK);
    }

    @GetMapping("/documents")
    public ResponseEntity<List<DocumentDTO>> getDocuments()
    {
        return new ResponseEntity<>(clientService.getAllDocuments(), HttpStatus.OK);
    }
}
