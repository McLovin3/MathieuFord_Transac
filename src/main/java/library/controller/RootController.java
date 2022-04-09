package library.controller;

import library.form.ClientForm;
import library.service.AttendantService;
import library.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        model.addAttribute("books", CLIENT_SERVICE.getAllDocuments());
        return "books";
    }

    //TODO must use DTO

    @GetMapping("/clients")
    public String getClientsRequest(Model model)
    {
        model.addAttribute("clients", ATTENDANT_SERVICE.getAllClients());
        return "clients";
    }

    @GetMapping("/createClient")
    public String getClientEdit(Model model)
    {
        model.addAttribute("clientForm", new ClientForm(0, "", ""));
        return "createClient";
    }


    @PostMapping("/createClient")
    public String postClient(@ModelAttribute ClientForm clientform, Model model)
    {
        ATTENDANT_SERVICE.createClient(clientform.name(), clientform.password());
        return "redirect:/";
    }
}
