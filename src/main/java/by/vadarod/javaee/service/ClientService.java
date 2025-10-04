package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.repository.ClientRepository;
import by.vadarod.javaee.repository.ClientRepositoryImpl;
import lombok.NonNull;

import java.util.List;

public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepositoryImpl repository) {
        this.clientRepository = repository;
    }

    public Long addClient(@NonNull Client client) {
        return clientRepository.addClient(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAll();
    }

    public void deleteClient(@NonNull Client client) {
        clientRepository.deleteClientById(client.getId());
    }

    public void changeClientStatus(Long clientId, Client.ClientStatus status) {
        Client client = clientRepository.findClientById(clientId);
        if (client != null) {
            client.setClientStatus(status);
            clientRepository.changeClient(client);
        }
    }

    public Client findClientById(Long clientId) {
        return clientRepository.findClientById(clientId);
    }

}
