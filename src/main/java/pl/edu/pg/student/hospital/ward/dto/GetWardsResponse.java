package pl.edu.pg.student.hospital.ward.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetWardsResponse {
    @Getter
    @Setter
    @Builder
    public static class WardEntry {
        private String name;
    }

    @Singular
    private List<WardEntry> wards;

    public static Function<Collection<Ward>, GetWardsResponse> entityToDtoMapper() {
        return wards -> {
            GetWardsResponseBuilder response = GetWardsResponse.builder();
            wards.stream()
                    .map(ward -> WardEntry.builder()
                            .name(ward.getName())
                            .build())
                    .forEach(response::ward);
            return response.build();
        };
    }
}
