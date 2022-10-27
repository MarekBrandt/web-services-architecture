package pl.edu.pg.student.hospital.ward.entity;

import lombok.*;

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
    private int numberOfBeds;
    private float areaInSquareMeters;
}
