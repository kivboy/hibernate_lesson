package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "sport_sch", name = "activities")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activitySeq")
    @SequenceGenerator(name = "activitySeq", schema = "sport_sch", sequenceName = "activity_seq", allocationSize = 1)
    private Long id;

    @Column (name = "activity_name", length=100, nullable = false)
    private String name;

    @Column (name = "price", precision = 10, scale = 2)
    private BigDecimal price;

    @OneToMany
    @JoinColumn (name = "activity_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Room> roomList = new ArrayList<>();
}
