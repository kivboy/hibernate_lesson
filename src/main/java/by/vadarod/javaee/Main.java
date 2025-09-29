package by.vadarod.javaee;

import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.repository.ClientRepositoryJpa;
import by.vadarod.javaee.service.ClientService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Client[] defaultClients = {
                new Client(1L, "Иванов", "Иван", 37, "375292340088", LocalDate.of(2025, 9, 29), Client.ClientStatus.ACTIVE, 250L),
                new Client(2L, "Петров", "Петр", 26, "375291234567", LocalDate.of(2025, 4, 15), Client.ClientStatus.BLOCKED, 140L),
                new Client(3L, "Сидоров", "Олег", 56, "375299871188", LocalDate.of(2025, 8, 25), Client.ClientStatus.PREMIUM, 1400L),
                new Client(4L, "Рабинович", "Мойша", 78, "375331232277", LocalDate.of(2025, 9, 29), Client.ClientStatus.PREMIUM, 1001L),
                new Client(5L, "Прохоров", "Сергей", 21, "375446581298", LocalDate.of(2025, 5, 21), Client.ClientStatus.ACTIVE, 100L),
        };

        ClientService clientService = new ClientService(new ClientRepositoryJpa());

        clientService.addClient(defaultClients[0]);
        clientService.addClient(defaultClients[1]);
        clientService.addClient(defaultClients[2]);
        clientService.addClient(defaultClients[3]);
        clientService.addClient(defaultClients[4]);

        List<Client> clients = clientService.getAllClients();
        System.out.println("Список всех клиентов в базе:");
        for (Client client:clients) {
            System.out.println(client);
        }

        clientService.deleteClient(clients.get(0));
        clientService.changeClientStatus(4L, Client.ClientStatus.PREMIUM);

        System.out.println("Список всех клиентов из базы");
        for (Client client:clientService.getAllClients()) {
            System.out.println(client);
        }
    }
}