package pl.edu.pg.student.hospital.person.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@EqualsAndHashCode
public class Patient implements Serializable {
    private Name name;
    private int age;
    private String pesel;
    private Ward ward;

    @Override
    public String toString() {
        return "Patient{" +
                "name=" + name +
                ", age=" + age +
                ", pesel='" + pesel + '\'' +
                ", ward=" + ward.getName() +
                '}';
    }
}
