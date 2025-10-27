package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Reservation;
import by.vadarod.javaee.entity.Room;
import by.vadarod.javaee.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Long addReservation(Reservation reservation) {
        return reservationRepository.addReservation(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.getAll();
    }

    public List<Reservation> getReservationsByRoomId(Long roomId, LocalDate reserveDate) {
        return reservationRepository.getReservationsByRoomId(roomId, reserveDate);
    }

    public List<Room> findRoomReservationsByClientAge(int minAge) {

        List<Reservation> reservationList =  reservationRepository.findReservationsByClientAge(minAge);

        HashSet<Room> roomHashSet = new HashSet<>();
        for (Reservation reservation:reservationList) {
            roomHashSet.add(reservation.getRoom());
        }

        return new ArrayList<>(roomHashSet);
    }
}
