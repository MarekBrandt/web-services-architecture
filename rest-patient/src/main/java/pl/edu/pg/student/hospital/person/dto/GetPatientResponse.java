package pl.edu.pg.student.hospital.person.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.student.hospital.person.entity.Patient;

import java.util.function.Function;

@Getter
@Setter
@Builder
public class GetPatientResponse {
    private String pesel;
    private String firstName;
    private String lastName;
    private int age;
    private String ward;

    public static Function<Patient, GetPatientResponse> entityToDtoMapper() {
        return patient -> GetPatientResponse.builder()
                .pesel(patient.getPesel())
                .firstName(patient.getFirstName())
                .lastName(patient.getLastName())
                .age(patient.getAge())
                .ward(patient.getWard().getName())
                .build();
    }
}
