package pl.edu.pg.student.hospital.person.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import pl.edu.pg.student.hospital.person.entity.Patient;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetPatientsResponse {
    @Getter
    @Setter
    @Builder
    public static class PatientEntry {
        private String pesel;
        private String firstName;
        private String lastName;
    }

    @Singular
    private List<PatientEntry> patients;

    public static Function<Collection<Patient>, GetPatientsResponse> entityToDtoMapper() {
        return patients -> {
            GetPatientsResponseBuilder response = GetPatientsResponse.builder();
            patients.stream()
                    .map(patient -> PatientEntry.builder()
                            .pesel(patient.getPesel())
                            .firstName(patient.getFirstName())
                            .lastName(patient.getLastName())
                            .build())
                    .forEach(response::patient);
            return response.build();
        };
    }
}
