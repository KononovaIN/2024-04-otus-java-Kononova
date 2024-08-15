package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.otus.model.Address;
import ru.otus.model.Client;
import ru.otus.model.Phone;

import java.util.Set;

public record ClientView(Long id, String name, String street, Set<Phone> phones) {
    public static ClientView mapClient(Client client, Address address) {
        return new ClientView(client.getId(), client.getName(), address.getStreet(), client.getPhones());
    }

    public static ClientView mapClient(Client client) {
        return new ClientView(client.getId(), client.getName(), null, client.getPhones());
    }
}
