package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(schema = "sport_sch", name = "activities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activitySeq")
    @SequenceGenerator(name = "activitySeq", schema = "sport_sch", sequenceName = "activity_seq", allocationSize = 1)
    private Long id;

    @Column (name = "activity_name", length=100)
    private String name;

    @Column (name = "price", precision = 10, scale = 2)
    private BigDecimal price;
}
