package by.vadarod.javaee.repository;

import by.vadarod.javaee.entity.Room;
import by.vadarod.javaee.entity.RoomUnder15;

import java.math.BigDecimal;
import java.util.List;

public interface RoomRepository {
    Long addRoom(Room room);
    List<Room> getAll();
    void deleteRoom(Long roomId);
    Room findRoomById(Long roomId);
    List<Room> findRoomsByCodes(List<String> idNumberList);
    Room updateRoomPricePerHour(Long roomId, BigDecimal price);
    Room addRoomPopulatedByRoomId(Long oldRoomId, String roomName, String roomIdNumber);
    List<RoomUnder15> findRoomsUnder15();
    int getMaxClientsAmount();
}
