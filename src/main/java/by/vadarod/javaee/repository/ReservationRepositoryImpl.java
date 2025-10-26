package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Client;
import by.vadarod.javaee.entity.Reservation;
import by.vadarod.javaee.entity.Room;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository{

    private final SessionFactory sessionFactory;

    public ReservationRepositoryImpl() {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Long addReservation(Reservation reservation) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(reservation);
        session.getTransaction().commit();
        session.close();

        return reservation.getId();
    }

    @Override
    public List<Reservation> getAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT r FROM Reservation r", Reservation.class);
        @SuppressWarnings("unchecked")
        List<Reservation> reservationList = (List<Reservation>)query.getResultList();
        session.close();

        return reservationList;
    }

    @Override
    public List<Reservation> getReservationsByRoomId(Long roomId, LocalDate reserveDate) {
        Session session = sessionFactory.openSession();
        Query query;

        if (reserveDate != null) {
            // передан параметр даты
            LocalDateTime startOfDay = reserveDate.atStartOfDay(); // 2025-10-19T00:00
            LocalDateTime endOfDay = reserveDate.atTime(LocalTime.MAX); // 2025-10-19T23:59:59.99

            query = session.createQuery("SELECT r FROM Reservation r WHERE (r.reserveDateTime BETWEEN :startOfDay AND :endOfDay) AND (r.room.id = :roomId)", Reservation.class);
            query.setParameter("startOfDay", startOfDay);
            query.setParameter("endOfDay", endOfDay);
        } else {
            // параметр даты не передан, выбираем все
            query = session.createQuery("SELECT r FROM Reservation r WHERE r.room.id = :roomId", Reservation.class);
        }

        query.setParameter("roomId", roomId);
        @SuppressWarnings("unchecked")
        List<Reservation> reservationList = (List<Reservation>)query.getResultList();
        session.close();

        return reservationList;
    }

    @Override
    public List<Room> findReservationsByClientAge(int minAge) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Reservation> query = cb.createQuery(Reservation.class);
        Root<Reservation> reservationRoot = query.from(Reservation.class);

        Join<Reservation, Client> clientJoin = reservationRoot.join("client");

        // WHERE person.age >= :minAge
        query.select(reservationRoot)
                .where(cb.greaterThanOrEqualTo(clientJoin.get("age"), minAge));

        List<Reservation> reservationList =  entityManager.createQuery(query).getResultList();

        HashSet<Room> roomHashSet = new HashSet<>();
        for (Reservation reservation:reservationList) {
            roomHashSet.add(reservation.getRoom());
        }

        return new ArrayList<>(roomHashSet);
    }
}
