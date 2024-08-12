package ru.otus.server;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.model.Client;
import ru.otus.service.DbServiceClientImpl;

import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {
    private final DbServiceClientImpl repository;

    @GetMapping("/clients")
    public String clientsListView(Model model){
        List<Client> clientList = repository.findAll();
        model.addAttribute("clients", clientList);

        return "clients";
    }
}
