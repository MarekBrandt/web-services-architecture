package pl.edu.pg.student.hospital.ward.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
}
