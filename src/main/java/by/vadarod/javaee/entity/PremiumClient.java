package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Where;

import java.time.LocalDate;

@Entity
@Table(schema = "sport_sch", name = "clients")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Immutable
@Where(clause = "status = 2")
public class PremiumClient {
    @Id
    private Long id;

    @Column(name = "last_name")
    private String lastName;
    @Column (name = "first_name")
    private String firstName;
    private int age;
    private String phone;
    @Column (name = "last_visit")
    private LocalDate lastVisit;
    @Column (name = "status")
    private Client.ClientStatus clientStatus;
    private Long amount;

    @Embedded
    private Address address;
}
