package pl.edu.pg.student.hospital.ward.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.student.hospital.person.repository.PatientRepository;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.repository.WardRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WardService {

    WardRepository wardRepository;

    PatientRepository patientRepository;

    @Autowired
    public WardService(WardRepository wardRepository, PatientRepository patientRepository) {
        this.wardRepository = wardRepository;
        this.patientRepository = patientRepository;
    }

    public Optional<Ward> find(String name) {
        return wardRepository.findById(name);
    }

    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    @Transactional
    public void save(Ward ward) {
        wardRepository.save(ward);
    }

    @Transactional
    public void update(Ward ward) {
        wardRepository.save(ward);
    }

    @Transactional
    public void delete(Ward ward) {
        wardRepository.delete(ward);
    }
}
