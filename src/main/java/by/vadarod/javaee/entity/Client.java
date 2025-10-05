package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table (schema = "sport_sch", name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clientSeq")
    @SequenceGenerator(name = "clientSeq", schema = "sport_sch", sequenceName = "client_seq", allocationSize = 1)
    private Long id;

    @Column (name = "last_name", length=100)
    private String lastName;
    @Column (name = "first_name", length=100)
    private String firstName;
    private int age;
    @Column (length=20)
    private String phone;
    @Column (name = "last_visit")
    private LocalDate lastVisit;
    @Column (name = "status")
    private ClientStatus clientStatus;
    private Long amount;

    @Embedded
    private Address address;


    public enum ClientStatus {
        ACTIVE("Активный"),
        BLOCKED("Заблокирован"),
        PREMIUM("Премиум");

        private final String statusName;

        ClientStatus(String statusName) {
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
