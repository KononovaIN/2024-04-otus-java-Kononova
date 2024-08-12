package ru.otus.server;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.dto.ClientRequest;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;
import ru.otus.service.DbServiceClientImpl;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
public class ClientRestController {
    private final DbServiceClientImpl repository;

    @PostMapping("/clients")
    public RedirectView saveClint(@RequestBody ClientRequest clientRequest){
        List<Phone> phoneList = Arrays.stream(clientRequest.phones().split(","))
                .map(Phone::new)
                .toList();

        Client client = new Client(clientRequest.name(), new Address(clientRequest.address()), phoneList);

        repository.saveClient(client);

        return new RedirectView("/clients", true);
    }
}
