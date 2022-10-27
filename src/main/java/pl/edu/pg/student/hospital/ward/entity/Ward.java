package pl.edu.pg.student.hospital.ward.entity;

import lombok.*;
import pl.edu.pg.student.hospital.person.entity.Patient;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "wards")
public class Ward implements Serializable {
    @Id
    private String name;
    private int numberOfBeds;
    private float areaInSquareMeters;

    @OneToMany(mappedBy = "ward")
    @ToString.Exclude
    private List<Patient> patients;
}
