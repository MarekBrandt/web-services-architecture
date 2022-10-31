package pl.edu.pg.student.hospital.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    List<Patient> findAllByWard(Ward ward);
    Optional<Patient> findByPeselAndWard(String pesel, Ward ward);
}
