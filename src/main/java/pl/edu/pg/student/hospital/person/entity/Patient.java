package pl.edu.pg.student.hospital.person.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "patients")
public class Patient implements Serializable {
    String firstName;
    String lastName;
    private int age;
    @Id
    private String pesel;
    @ManyToOne
    @JoinColumn(name = "ward")
    private Ward ward;

    @Override
    public String toString() {
        return "Patient{" +
                "name=" + firstName + " " + lastName+
                ", age=" + age +
                ", pesel='" + pesel + '\'' +
                ", ward=" + ward.getName() +
                '}';
    }
}
