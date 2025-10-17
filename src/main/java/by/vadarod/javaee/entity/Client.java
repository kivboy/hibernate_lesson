package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "sport_sch", name = "clients")
@PrimaryKeyJoinColumn(name="person_id")
public class Client extends Person {

    @Column (name = "last_visit")
    private LocalDate lastVisit;
    @Column (name = "status")
    private ClientStatus clientStatus;
    private Long amount;

    public Client(Long id, String lastName, String firstName, int age,
           String phone, LocalDate lastVisit, ClientStatus clientStatus, Long amount, Address address) {

        this.setId(id);
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setAge(age);
        this.setPhone(phone);
        this.setLastVisit(lastVisit);
        this.setClientStatus(clientStatus);
        this.setAmount(amount);
        this.setAddress(address);
    }

    @Override
    public String toString() {
        return String.format(
                "Client{%s, lastVisit=%tF, clientStatus=%s, amount=%d}",
                super.toString(),
                lastVisit, clientStatus, amount
        );
    }

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
