package by.vadarod.javaee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Immutable
@Subselect("SELECT id,capacity,id_number,room_name,price_1h,status FROM sport_sch.rooms WHERE capacity <= 15")

public class RoomUnder15 {
    @Id
    private Long id;

    @Column(name = "room_name")
    private String name;
    @Column (name = "id_number")
    private String idNumber;
    private int capacity;

    @Column (name = "status")
    private Room.RoomStatus roomStatus;

    @Column (name = "price_1h", precision = 10, scale = 2)
    private BigDecimal pricePerHour;
}
