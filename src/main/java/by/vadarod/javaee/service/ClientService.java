package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.repository.ClientRepository;
import by.vadarod.javaee.repository.ClientRepositoryJpa;
import lombok.NonNull;

import java.util.List;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepositoryJpa repository) {
        this.clientRepository = repository;
    }

    public void addClient(@NonNull Client client) {
        clientRepository.addClient(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public void deleteClient(@NonNull Client client) {
        clientRepository.deleteClient(client);
    }

    public void changeClientStatus(Long clientId, Client.ClientStatus status) {
        Client client = clientRepository.findClientById(clientId);
        if (client != null) {
            client.setClientStatus(status);
            clientRepository.changeClient(client);
        }
    }

}
