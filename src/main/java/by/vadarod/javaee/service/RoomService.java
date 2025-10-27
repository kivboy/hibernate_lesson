package by.vadarod.javaee.service;

import by.vadarod.javaee.entity.Room;
import by.vadarod.javaee.entity.RoomUnder15;
import by.vadarod.javaee.repository.RoomRepository;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Long addRoom(@NonNull Room room) {
        return roomRepository.addRoom(room);
    }

    public Room findRoomById(Long roomId) {
        return roomRepository.findRoomById(roomId);
    }

    public Room updateRoomPricePerHour(Long roomId, BigDecimal pricePerHour) {
        return roomRepository.updateRoomPricePerHour(roomId, pricePerHour);
    }

    public Room addRoomPopulatedByRoomId(Long oldRoomId, String roomName, String roomIdNumber) {
        return roomRepository.addRoomPopulatedByRoomId(oldRoomId, roomName, roomIdNumber);
    }

    public List<Room> getAllRooms() {
        return roomRepository.getAll();
    }

    public List<RoomUnder15> findRoomsUnder15() {
        return roomRepository.findRoomsUnder15();
    }

    public List<Room> findRoomsByCodes(List<String> idNumberList) {
        return roomRepository.findRoomsByCodes(idNumberList);
    }

    public void deleteRoom(Long roomId) {
        roomRepository.deleteRoom(roomId);
    }

    public int getMaxClientsAmount() {
        return roomRepository.getMaxClientsAmount();
    }
}
