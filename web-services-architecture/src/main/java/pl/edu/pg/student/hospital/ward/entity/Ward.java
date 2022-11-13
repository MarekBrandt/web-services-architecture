package pl.edu.pg.student.hospital.ward.entity;

import lombok.*;
import pl.edu.pg.student.hospital.person.entity.Patient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
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
    @Column(name = "number_of_beds")
    private int numberOfBeds;
    @Column(name = "area_in_square_meters")
    private float areaInSquareMeters;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "ward")
    @ToString.Exclude
    private List<Patient> patients;
}
