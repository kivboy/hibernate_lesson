package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Reservation;
import by.vadarod.javaee.entity.Room;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {
    Long addReservation(Reservation reservation);
    List<Reservation> getAll();
    List<Reservation> getReservationsByRoomId(Long roomId, LocalDate reserveDate);
    List<Room> findReservationsByClientAge(int minAge);
}
