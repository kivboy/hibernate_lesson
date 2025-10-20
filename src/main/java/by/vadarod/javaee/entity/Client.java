package by.vadarod.javaee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    @Column (name = "amount", precision = 10, scale = 2)
    private BigDecimal amount;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Visit> visitList = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Reservation> reservationList = new ArrayList<>();

    public Client(Long id, String lastName, String firstName, int age,
           String phone, LocalDate lastVisit, ClientStatus clientStatus, BigDecimal amount, Address address) {

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
                "Client{%s, lastVisit=%tF, clientStatus=%s, amount=%.2f}",
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
