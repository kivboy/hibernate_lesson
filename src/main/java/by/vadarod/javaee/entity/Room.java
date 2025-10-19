package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "sport_sch", name = "rooms")
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomSeq")
    @SequenceGenerator(name = "roomSeq", schema = "sport_sch", sequenceName = "room_seq", allocationSize = 1)
    private Long id;

    @Column (name = "room_name", length=100, nullable = false)
    private String name;
    @Column (name = "id_number", length=50, nullable = false, unique = true)
    private String idNumber;
    private int capacity;

    @Column (name = "status")
    private Room.RoomStatus roomStatus;

    @Column (name = "price_1h", precision = 10, scale = 2)
    private BigDecimal pricePerHour;

    @OneToMany(mappedBy = "room", cascade = CascadeType.REMOVE)
    private List<Reservation> reservationList = new ArrayList<>();

    public Room(Long roomId, String roomName, String roomCode, int capacity, Room.RoomStatus roomStatus, BigDecimal pricePerHour) {
        this.setId(roomId);
        this.setName(roomName);
        this.setIdNumber(roomCode);
        this.setCapacity(capacity);
        this.setRoomStatus(roomStatus);
        this.setPricePerHour(pricePerHour);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", capacity=" + capacity +
                ", roomStatus=" + roomStatus +
                ", pricePerHour=" + pricePerHour +
                '}';
    }

    public enum RoomStatus {
        ACTIVE("Активно"),
        REPAIR("На ремонте");

        private final String statusName;

        RoomStatus(String statusName) {
            this.statusName = statusName;
        }

        public String getStatusName() {
            return statusName;
        }

        @Override
        public String toString() {
            return statusName;
        }
    }
}
