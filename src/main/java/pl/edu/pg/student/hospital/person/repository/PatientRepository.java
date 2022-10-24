package pl.edu.pg.student.hospital.person.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.student.hospital.datastore.DataStore;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.repository.Repository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class PatientRepository implements Repository<Patient, String> {

    private DataStore dataStore;

    @Autowired
    public PatientRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Patient> find(String pesel) {
        return dataStore.findPatient(pesel);
    }

    @Override
    public List<Patient> findAll() {
        return dataStore.findAllPatients();
    }

    @Override
    public void save(Patient patient) {
        dataStore.savePatient(patient);
    }

    @Override
    public void delete(Patient patient) {
        dataStore.deletePatient(patient);
    }
}
