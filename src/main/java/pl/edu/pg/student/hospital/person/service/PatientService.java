package pl.edu.pg.student.hospital.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.person.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> find(String pesel) {
        return patientRepository.find(pesel);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }
}
