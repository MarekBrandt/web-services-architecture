package pl.edu.pg.student.hospital.person.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
public class PostPatientRequest {
    private String pesel;
    private String firstName;
    private String lastName;
    private int age;
    private String ward;

    public static Function<PostPatientRequest, Patient> dtoToEntityMapper(Supplier<Ward> wardSupplier) {
        return request -> Patient.builder()
                .pesel(request.getPesel())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .ward(wardSupplier.get())
                .build();
    }

}
