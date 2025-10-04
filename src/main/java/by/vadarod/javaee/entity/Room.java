package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "sport_sch", name = "rooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomSeq")
    @SequenceGenerator(name = "roomSeq", schema = "sport_sch", sequenceName = "room_seq", allocationSize = 1)
    private Long id;

    @Column (name = "room_name", length=100)
    private String name;
    @Column (name = "id_number", length=50)
    private String idNumber;
    private int capacity;

    @Column (name = "status")
    private Room.RoomStatus roomStatus;

    @Column (name = "price_1h", precision = 10, scale = 2)
    private BigDecimal pricePerHour;

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
