package pl.edu.pg.student.hospital.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.hospital.person.entity.Patient;

@org.springframework.stereotype.Repository
public interface PatientRepository extends JpaRepository<Patient, String> {

}
