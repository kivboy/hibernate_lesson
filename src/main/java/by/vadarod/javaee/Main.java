package by.vadarod.javaee;

import by.vadarod.javaee.entity.Activity;
import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.entity.Room;
import by.vadarod.javaee.repository.ActivityRepositoryImpl;
import by.vadarod.javaee.repository.ClientRepositoryImpl;
import by.vadarod.javaee.repository.RoomRepositoryImpl;
import by.vadarod.javaee.service.ActivityService;
import by.vadarod.javaee.service.ClientService;
import by.vadarod.javaee.service.RoomService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Client[] defaultClients = {
                new Client(null, "Иванов", "Иван", 37, "375292340088", LocalDate.of(2025, 9, 29), Client.ClientStatus.ACTIVE, 250L),
                new Client(null, "Петров", "Петр", 26, "375291234567", LocalDate.of(2025, 4, 15), Client.ClientStatus.BLOCKED, 140L),
                new Client(null, "Сидоров", "Олег", 56, "375299871188", LocalDate.of(2025, 8, 25), Client.ClientStatus.PREMIUM, 1400L),
                new Client(null, "Рабинович", "Мойша", 78, "375331232277", LocalDate.of(2025, 9, 29), Client.ClientStatus.PREMIUM, 1001L),
                new Client(null, "Прохоров", "Сергей", 21, "375446581298", LocalDate.of(2025, 5, 21), Client.ClientStatus.ACTIVE, 100L)
        };

        ClientService clientService = new ClientService(new ClientRepositoryImpl());

        clientService.addClient(defaultClients[0]);
        clientService.addClient(defaultClients[1]);
        clientService.addClient(defaultClients[2]);
        clientService.addClient(defaultClients[3]);
        Long clientId = clientService.addClient(defaultClients[4]);

        Client client = clientService.findClientById(clientId);
        if (client != null) {
            System.out.println("Найден клиент по Id: " + client);
            System.out.println("Блокируем клиента с Id " + client.getId());
            clientService.changeClientStatus(client.getId(), Client.ClientStatus.BLOCKED);
        } else {
            System.out.println("Клиент не найден!");
        }

        Activity[] defaultActivities = {
                new Activity(null, "Тренажерный зал", new BigDecimal("10.00").setScale(2, RoundingMode.UP)),
                new Activity(null, "Бассейн", new BigDecimal("15.00").setScale(2, RoundingMode.UP)),
                new Activity(null, "Сауна", new BigDecimal("25.00").setScale(2, RoundingMode.UP))
        };

        ActivityService activityService = new ActivityService(new ActivityRepositoryImpl());

        activityService.addActivity(defaultActivities[0]);
        activityService.addActivity(defaultActivities[1]);
        activityService.addActivity(defaultActivities[2]);

        Room[] defaultRoms = {
                new Room(null, "Зал №1", "A1.01.00", 20, Room.RoomStatus.ACTIVE, new BigDecimal("100.00").setScale(2, RoundingMode.UP)),
                new Room(null, "Бассейн №1", "B1.01.00", 50, Room.RoomStatus.ACTIVE, new BigDecimal("500.00").setScale(2, RoundingMode.UP)),
                new Room(null, "Сауна №1","S1.01.00", 10, Room.RoomStatus.REPAIR, new BigDecimal("150.00").setScale(2, RoundingMode.UP))
        };

        RoomService roomService = new RoomService(new RoomRepositoryImpl());

        Long roomId = roomService.addRoom(defaultRoms[0]);
        roomService.addRoom(defaultRoms[1]);
        roomService.addRoom(defaultRoms[2]);


        Room room = roomService.findRoomById(roomId);
        if (room != null) {
            System.out.println("Найдено помещение по Id: " + room);

            room = roomService.updateRoomPricePerHour(roomId, new BigDecimal("120.00").setScale(2, RoundingMode.UP));
            System.out.println("Обновлена стоимость аренды: " + room);

            room = roomService.addRoomPopulatedByRoomId(roomId, "Зал №2", "A1.02.00");
            System.out.println("Добавлено дополнительное помещение: " + room);
        } else {
            System.out.println("Помещение не найдено!");
        }

        // вывод текущего состояния БД

        List<Client> clients = clientService.getAllClients();
        System.out.println("Список всех клиентов в базе:");
        for (Client tmpClient:clients) {
            System.out.println(tmpClient);
        }

        List<Room> roomList = roomService.getAllRooms();
        System.out.println("Список всех помещений в базе:");
        for (Room tmpRoom:roomList) {
            System.out.println(tmpRoom);
        }

        List<Activity> activityList = activityService.getAllActivities();
        System.out.println("Список всех услуг в базе:");
        for (Activity tmpActivity:activityList) {
            System.out.println(tmpActivity);
        }

    }
}