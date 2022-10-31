package pl.edu.pg.student.hospital.person.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.student.hospital.person.entity.Patient;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
public class PutPatientRequest {
    private String firstName;
    private String lastName;
    private int age;

    public static BiFunction<Patient, PutPatientRequest, Patient> dtoToEntityMapper() {
        return (patient, request) -> {
                    patient.setFirstName(request.getFirstName());
                    patient.setLastName(request.getLastName());
                    patient.setAge(request.getAge());
                    return patient;
        };
    }
}
