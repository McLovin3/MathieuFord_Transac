package library.controller;

import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("books", CLIENT_SERVICE.getAllDocuments());
        return "books";
    }

    @GetMapping("/clients")
    public String getClientsRequest(Model model)
    {
        model.addAttribute("clients", ATTENDANT_SERVICE.getAllClients());
        return "clients";
    }
}
