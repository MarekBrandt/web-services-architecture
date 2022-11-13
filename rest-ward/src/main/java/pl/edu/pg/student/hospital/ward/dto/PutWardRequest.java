package pl.edu.pg.student.hospital.ward.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
public class PutWardRequest {
    private int numberOfBeds;
    private float areaInSquareMeters;

    public static BiFunction<Ward, PutWardRequest, Ward> dtoToEntityMapper() {
        return (ward, request) -> {
                    ward.setNumberOfBeds(request.getNumberOfBeds());
                    ward.setAreaInSquareMeters(request.getAreaInSquareMeters());
                    return ward;
        };
    }
}
