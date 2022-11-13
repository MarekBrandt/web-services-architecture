package pl.edu.pg.student.hospital.ward.entity;

import lombok.*;
import pl.edu.pg.student.hospital.person.entity.Patient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wards")
public class Ward implements Serializable {
    @Id
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ward")
    @ToString.Exclude
    private List<Patient> patients;
}
