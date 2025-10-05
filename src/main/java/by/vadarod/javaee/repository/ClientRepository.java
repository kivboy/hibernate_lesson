package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.entity.PremiumClient;

import java.util.List;

public interface ClientRepository {
    Long addClient(Client client);
    List<Client> getAll();
    void deleteClientById(Long clientId);
    Client findClientById(Long id);
    void changeClient(Client client);
    List<PremiumClient> findAllPremiumClients();

}
