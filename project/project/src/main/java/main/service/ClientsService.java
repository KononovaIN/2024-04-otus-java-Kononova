package main.service;

import java.util.List;
import main.entity.Clients;

public interface ClientsService {

  List<Clients> listClients();

  Clients findClient(Long id);

  Clients addClient(Clients client);

  void deleteClient(Long id);
}

