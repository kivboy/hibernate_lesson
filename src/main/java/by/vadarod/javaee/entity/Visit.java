package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(schema = "sport_sch", name = "visits")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visitSeq")
    @SequenceGenerator(name = "visitSeq", schema = "sport_sch", sequenceName = "visit_seq", allocationSize = 1)
    private Long id;

    @Column (name = "visit_date")
    private LocalDate visitDate;

    @Column (name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
