package pl.edu.pg.student.hospital.ward.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@ToString
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class Ward implements Serializable {
    private String name;
    private int numberOfBeds;
    private float areaInSquareMeters;

}
