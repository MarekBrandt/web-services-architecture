package pl.edu.pg.student.hospital.ward.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetWardResponse {
    private String name;
    private int numberOfBeds;
    private float areaInSquareMeters;

    public static Function<Ward, GetWardResponse> entityToDtoMapper() {
        return ward -> GetWardResponse.builder()
                .name(ward.getName())
                .numberOfBeds(ward.getNumberOfBeds())
                .areaInSquareMeters(ward.getAreaInSquareMeters())
                .build();
    }
}
