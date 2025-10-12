package by.vadarod.javaee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(schema = "sport_sch", name = "employees")
@PrimaryKeyJoinColumn(name="person_id")
public class Employee extends Person {

    public Employee(Long id, String lastName, String firstName, int age,
                    String phone, LocalDate employmentDate, LocalDate dismissalDate,
                    String jobTitle, Long salary, Address address) {

        this.setId(id);
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setAge(age);
        this.setPhone(phone);
        this.setEmploymentDate(employmentDate);
        this.setDismissalDate(dismissalDate);
        this.setJobTitle(jobTitle);
        this.setSalary(salary);
        this.setAddress(address);
    }

    @Override
    public String toString() {
        return String.format(
                "Employee{%s, employmentDate=%tF, dismissalDate=%tF, jobTitle=%s, salary=%d}",
                super.toString(),
                employmentDate, dismissalDate, jobTitle, salary
        );
    }

    @Column(name = "employment_date")
    LocalDate employmentDate;
    @Column(name = "dismissal_date")
    LocalDate dismissalDate;
    @Column(name = "job_title", length = 100)
    String jobTitle;
    Long salary;
}
