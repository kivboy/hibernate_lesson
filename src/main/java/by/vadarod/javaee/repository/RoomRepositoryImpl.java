package by.vadarod.javaee.repository;

import by.vadarod.javaee.config.HibernateSessionFactoryUtil;
import by.vadarod.javaee.entity.Room;
import by.vadarod.javaee.entity.RoomUnder15;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository{

    private final SessionFactory sessionFactory;

    public RoomRepositoryImpl() {
        this.sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
    }

    @Override
    public Long addRoom(Room room) {
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.persist(room);
        session.getTransaction().commit();
        session.close();

        return room.getId();
    }

    @Override
    public List<Room> getlAll() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select r from Room r", Room.class);
        @SuppressWarnings("unchecked")
        List<Room> roomList = (List<Room> )query.getResultList();
        session.close();

        return roomList;
    }

    @Override
    public Room findRoomById(Long roomId) {
        Session session = sessionFactory.openSession();
        Room room = session.get(Room.class, roomId);
        session.close();

        return room;
    }

    @Override
    public Room updateRoomPricePerHour(Long roomId, BigDecimal price) {
        Session session = sessionFactory.openSession();
        Room room = session.get(Room.class, roomId);
        if (room != null) {
            room.setPricePerHour(price);
            session.beginTransaction();
            session.merge(room);
            session.getTransaction().commit();
        }
        session.close();

        return room;
    }

    @Override
    public Room addRoomPopulatedByRoomId(Long oldRoomId, String roomName, String roomIdNumber) {
        Session session = sessionFactory.openSession();
        Room room = session.get(Room.class, oldRoomId);
        if (room != null) {
            session.detach(room);
            room.setId(null);
            room.setIdNumber(roomIdNumber);
            room.setName(roomName);
            session.getTransaction().begin();
            session.merge(room);
            session.getTransaction().commit();
        }

        session.close();
        return room;
    }

    @Override
    public List<RoomUnder15> findRoomsUnder15() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select r from RoomUnder15 r", RoomUnder15.class);
        @SuppressWarnings("unchecked")
        List<RoomUnder15> roomList = (List<RoomUnder15>)query.getResultList();
        session.close();

        return roomList;
    }

}
