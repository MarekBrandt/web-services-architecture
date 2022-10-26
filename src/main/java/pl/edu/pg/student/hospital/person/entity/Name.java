package pl.edu.pg.student.hospital.person.entity;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Name implements Serializable {
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "Name: " + firstName + " " + lastName;
    }
}
