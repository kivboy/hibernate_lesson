package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Client;

import java.util.List;

public interface ClientRepository {
    void addClient(Client client);
    List<Client> getAll();
    void deleteClient(Client client);
    Client findClientById(Long id);
    void changeClient(Client client);
}
