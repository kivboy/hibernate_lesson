package by.vadarod.javaee;

import by.vadarod.javaee.entity.*;
import by.vadarod.javaee.repository.*;
import by.vadarod.javaee.service.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Клиенты

        Client[] defaultClients = {
                new Client(null, "Иванов", "Иван", 37, "375292340088", LocalDate.of(2025, 9, 29), Client.ClientStatus.ACTIVE,
                        new BigDecimal("250.70").setScale(2, RoundingMode.UP),
                        new Address("Минск","Тимирязева","23","220091")),
                new Client(null, "Петров", "Петр", 26, "375291234567", LocalDate.of(2025, 4, 15), Client.ClientStatus.BLOCKED,
                        new BigDecimal("140.30").setScale(2, RoundingMode.UP),
                        new Address("Минск","Притыцкого","15","220129")),
                new Client(null, "Сидоров", "Олег", 56, "375299871188", LocalDate.of(2025, 8, 25), Client.ClientStatus.PREMIUM,
                        new BigDecimal("1400.10").setScale(2, RoundingMode.UP),
                        new Address("Фаниполь","Радужная","5","320077")),
                new Client(null, "Рабинович", "Мойша", 78, "375331232277", LocalDate.of(2025, 9, 29), Client.ClientStatus.PREMIUM,
                        new BigDecimal("1001.00").setScale(2, RoundingMode.UP),
                        new Address("Минск","Весенняя","1","221356")),
                new Client(null, "Прохоров", "Сергей", 21, "375446581298", LocalDate.of(2025, 5, 21), Client.ClientStatus.ACTIVE,
                        new BigDecimal("100.00").setScale(2, RoundingMode.UP),
                        new Address("Минск","Рафиева","78","267089"))
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

        // Сотрудники

        Employee[] defaultEmployees = {
                new Employee(null, "Гончаревич", "Василий", 45, "375298880018", LocalDate.of(2025, 3, 20), null, "Фитнес инструктор",3550L,
                        new Address("Минск","Тихая","15","220231")),
                new Employee(null, "Аниськова", "Жанна", 25, "375251112369", LocalDate.of(2024, 6, 23), null, "Уборщик",1000L,
                        new Address("Минск","Радиальная","76","220001")),
                new Employee(null, "Черепович", "Александр", 34, "375291872256", LocalDate.of(2023, 11, 9), null, "Фитнес инструктор",2500L,
                        new Address("Минск","Чичурина","9","220201")),
                new Employee(null, "Сазонов", "Виктор", 44, "375297442277", LocalDate.of(2025, 6, 15), null, "Директор",4500L,
                        new Address("Минск","Радужная","1","222358"))
        };

        EmployeeService employeeService = new EmployeeService(new EmployeeRepositoryImpl());

        employeeService.addEmployee(defaultEmployees[0]);
        employeeService.addEmployee(defaultEmployees[1]);
        employeeService.addEmployee(defaultEmployees[2]);
        employeeService.addEmployee(defaultEmployees[3]);

        // Помещения

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

        List<RoomUnder15> smallRoomsList = roomService.findRoomsUnder15();
        System.out.println("Список помещений вместимостью меньше 15 человек:");
        for (RoomUnder15 smallRoom:smallRoomsList) {
            System.out.println(smallRoom);
        }

        PrintCurrentDbStatus(null, null, roomService, null, null, null);

        // Услуги

        Activity[] defaultActivities = {
                new Activity(null, "Тренажерный зал", new BigDecimal("10.00").setScale(2, RoundingMode.UP),
                        roomService.findRoomsByCodes(List.of("A1.01.00", "A1.02.00"))),
                new Activity(null, "Бассейн", new BigDecimal("15.00").setScale(2, RoundingMode.UP),
                        roomService.findRoomsByCodes(List.of("B1.01.00"))),
                new Activity(null, "Сауна", new BigDecimal("25.00").setScale(2, RoundingMode.UP),
                        roomService.findRoomsByCodes(List.of("S1.01.00")))
        };

        ActivityService activityService = new ActivityService(new ActivityRepositoryImpl());

        activityService.addActivity(defaultActivities[0]);
        activityService.addActivity(defaultActivities[1]);
        activityService.addActivity(defaultActivities[2]);

        // Визиты:

        VisitService visitService = new VisitService(new VisitRepositoryImpl());
        client = clientService.findClientById(1L);
        if (client != null) {
            Visit visit = new Visit(null, LocalDate.now().minusDays(1),
                    new BigDecimal("15.00").setScale(2, RoundingMode.UP),
                    client);
            visitService.addVisit(visit);

            visit = new Visit(null, LocalDate.now(),
                    new BigDecimal("18.50").setScale(2, RoundingMode.UP),
                    client);
            visitService.addVisit(visit);
        }

        client = clientService.findClientById(2L);
        if (client != null) {
            Visit visit = new Visit(null, LocalDate.now().minusDays(2),
                    new BigDecimal("11.20").setScale(2, RoundingMode.UP),
                    client);
            visitService.addVisit(visit);

            visit = new Visit(null, LocalDate.now(),
                    new BigDecimal("14.50").setScale(2, RoundingMode.UP),
                    client);
            visitService.addVisit(visit);
        }

        // Записи
        ReservationService reservationService = new ReservationService(new ReservationRepositoryImpl());
        client = clientService.findClientById(1L);

        if (client != null) {
            room = roomService.findRoomsByCodes(List.of("S1.01.00")).getFirst();
            if (room != null) {
                Reservation reservation = new Reservation(null, client, room, LocalDateTime.now());
                reservationService.addReservation(reservation);
            }

            room = roomService.findRoomsByCodes(List.of("B1.01.00")).getFirst();
            if (room != null) {
                Reservation reservation = new Reservation(null, client, room, LocalDateTime.now());
                reservationService.addReservation(reservation);
            }
        }

        client = clientService.findClientById(2L);

        if (client != null) {
            room = roomService.findRoomsByCodes(List.of("S1.01.00")).getFirst();
            if (room != null) {
                Reservation reservation = new Reservation(null, client, room, LocalDateTime.now());
                reservationService.addReservation(reservation);
            }

            room = roomService.findRoomsByCodes(List.of("B1.01.00")).getFirst();
            if (room != null) {
                Reservation reservation = new Reservation(null, client, room, LocalDateTime.now());
                reservationService.addReservation(reservation);
            }
        }

        // список бронирований
        PrintCurrentDbStatus(null, null, null, null, null, reservationService);

        System.out.println("Удаляем клиента = должны удалиться каскадно так же записи визитов и бронирований");
        client = clientService.findClientById(1L);
        if (client != null) {
            clientService.deleteClient(client);
        }

        PrintCurrentDbStatus(null, null, null, null, null, reservationService);

        System.out.println("Получаем помещение:");
        room = roomService.findRoomsByCodes(List.of("B1.01.00")).getFirst();
        System.out.println("Удаляем помещение = должны удалиться каскадно так же записи визитов и бронирований");
        if (room != null) {
            roomService.deleteRoom(room.getId());
        }

        // вывод текущего состояния БД
        PrintCurrentDbStatus(clientService,
                employeeService, roomService, activityService, visitService, reservationService);

    }


    static void PrintCurrentDbStatus(ClientService clientService,
                                     EmployeeService employeeService,
                                     RoomService roomService,
                                     ActivityService activityService,
                                     VisitService visitService,
                                     ReservationService reservationService) {

        // Вывод текущего состояния БД

        if (clientService != null) {
            List<Client> clients = clientService.getAllClients();
            System.out.println("Список всех клиентов в базе:");
            for (Client tmpClient : clients) {
                System.out.println(tmpClient);
            }
        }

//        List<PremiumClient> premiumClients = clientService.findAllPremiumClients();
//        System.out.println("Список премиум клиентов:");
//        for (PremiumClient premiumClient:premiumClients) {
//            System.out.println(premiumClient);
//        }

        if (employeeService != null) {
            List<Employee> employeeList = employeeService.getAllEmployees();
            System.out.println("Список всех работников в базе:");
            for (Employee tmpEmployee : employeeList) {
                System.out.println(tmpEmployee);
            }
        }

        if (roomService != null) {
            List<Room> roomList = roomService.getAllRooms();
            System.out.println("Список всех помещений в базе:");
            for (Room tmpRoom : roomList) {
                System.out.println(tmpRoom);
            }
        }

        if (activityService != null) {
            List<Activity> activityList = activityService.getAllActivities();
            System.out.println("Список всех услуг в базе:");
            for (Activity tmpActivity : activityList) {
                System.out.println(tmpActivity);
            }
        }

        if (visitService != null) {
            List<Visit> visitList = visitService.getAllVisits();
            System.out.println("Список всех визитов в базе:");
            for (Visit tmpVisit:visitList) {
                System.out.println(tmpVisit);
            }
        }

        if (reservationService != null) {
            List<Reservation> reservationList = reservationService.getAllReservations();
            System.out.println("Список всех бронирований в базе:");
            for (Reservation tmpReservation:reservationList) {
                System.out.println(tmpReservation);
            }
        }

    }
}