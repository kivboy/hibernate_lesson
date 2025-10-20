package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {
    Long addReservation(Reservation reservation);
    List<Reservation> getAll();
    List<Reservation> getReservationsByRoomId(Long roomId, LocalDate reserveDate);
}
