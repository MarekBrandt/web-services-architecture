package pl.edu.pg.student.hospital.datastore;

import lombok.Getter;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.serialization.CloningUtility;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.*;

@Getter
@Component
public class DataStore {
    private Set<Ward> wards = new HashSet<>();
    private Set<Patient> patients = new HashSet<>();

    public synchronized Optional<Ward> findWard(String name) {
        return wards.stream()
                .filter(ward -> ward.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<Ward> findAllWards() {
        return new ArrayList<>(wards);
    }

    public synchronized void saveWard(Ward ward) {
        findWard(ward.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The ward \"%s\" is not unique", ward.getName())
                    );
                },
                () -> wards.add(CloningUtility.clone(ward))
        );
    }

    public synchronized void deleteWard(Ward ward) {
        findWard(ward.getName()).ifPresentOrElse(
                original -> wards.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The ward named: \"%s\" does not exist", ward.getName())
                    );
                }
        );
    }

    public synchronized Optional<Patient> findPatient(String pesel) {
        return patients.stream()
                .filter(patient -> patient.getPesel().equals(pesel))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<Patient> findAllPatients() {
        return new ArrayList<>(patients);
    }

    public synchronized void savePatient(Patient patient) {
        findPatient(patient.getPesel()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The patient pesel \"%s\" is not unique", patient.getPesel())
                    );
                },
                () -> patients.add(CloningUtility.clone(patient))
        );
    }

    public synchronized void deletePatient(Patient patient) {
        findPatient(patient.getPesel()).ifPresentOrElse(
                original -> patients.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The patient with pesel: \"%s\" does not exist", patient.getPesel())
                    );
                }
        );
    }
}
