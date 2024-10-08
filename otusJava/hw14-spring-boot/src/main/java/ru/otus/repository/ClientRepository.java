package ru.otus.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.otus.model.Client;

import java.util.Optional;

public interface ClientRepository extends ListCrudRepository<Client, Long> {
}
