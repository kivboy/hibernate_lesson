package by.vadarod.javaee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Embeddable
public class Address {
    @Column(length=50)
    private String city;
    @Column (length=100)
    private String street;
    @Column (length=20)
    private String build;
    @Column (length=20)
    private String postcode;
}
