package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@Table(schema = "sport_sch", name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSeq")
    @SequenceGenerator(name = "personSeq", schema = "sport_sch", sequenceName = "person_seq", allocationSize = 1)
    private Long id;

    @Column(name = "last_name", length=100)
    private String lastName;
    @Column (name = "first_name", length=100)
    private String firstName;
    private int age;
    @Column (length = 20)
    private String phone;

    @Embedded
    private Address address;
}
