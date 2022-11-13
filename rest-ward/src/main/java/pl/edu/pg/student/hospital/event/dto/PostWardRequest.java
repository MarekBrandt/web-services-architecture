package pl.edu.pg.student.hospital.event.dto;

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

    public static Function<Ward, PostWardRequest> entityToDtoMapper() {
        return ward -> PostWardRequest.builder()
                .name(ward.getName())
                .build();
    }
}
