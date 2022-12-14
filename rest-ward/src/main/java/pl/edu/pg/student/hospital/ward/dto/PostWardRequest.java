package pl.edu.pg.student.hospital.ward.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class PostWardRequest {
    private String name;
    private int numberOfBeds;
    private float areaInSquareMeters;

    public static Function<PostWardRequest, Ward> dtoToEntityMapper() {
        return request -> Ward.builder()
                .name(request.getName())
                .numberOfBeds(request.getNumberOfBeds())
                .areaInSquareMeters(request.getAreaInSquareMeters())
                .build();
    }

}
