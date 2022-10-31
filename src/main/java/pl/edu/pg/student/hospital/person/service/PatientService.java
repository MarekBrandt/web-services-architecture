package pl.edu.pg.student.hospital.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.person.repository.PatientRepository;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.repository.WardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepository;

    WardRepository wardRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository, WardRepository wardRepository) {
        this.patientRepository = patientRepository;
        this.wardRepository = wardRepository;
    }

    public Optional<Patient> find(String pesel) {
        return patientRepository.findById(pesel);
    }

    public Optional<Patient> find(String pesel, String ward) {
        return wardRepository.findById(ward).flatMap(w -> patientRepository.findByPeselAndWard(pesel, w));
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public List<Patient> findAll(Ward ward) {
        return patientRepository.findAllByWard(ward);
    }

    @Transactional
    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    @Transactional
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    @Transactional
    public void delete(Patient patient) {
        patientRepository.delete(patient);
    }
}
