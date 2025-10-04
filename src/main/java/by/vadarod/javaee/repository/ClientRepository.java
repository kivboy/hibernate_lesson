package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Client;

import java.util.List;

public interface ClientRepository {
    Long addClient(Client client);
    List<Client> getAll();
    void deleteClientById(Long clientId);
    Client findClientById(Long id);
    void changeClient(Client client);

}
