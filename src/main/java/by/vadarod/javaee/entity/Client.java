package by.vadarod.javaee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;

    @Column (name = "last_name")
    private String lastName;
    @Column (name = "first_name")
    private String firstName;
    private int age;
    private String phone;
    @Column (name = "last_visit")
    private LocalDate lastVisit;
    @Column (name = "status")
    private ClientStatus clientStatus;
    private Long amount;

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
