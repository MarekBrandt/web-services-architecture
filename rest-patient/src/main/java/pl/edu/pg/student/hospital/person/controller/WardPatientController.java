package pl.edu.pg.student.hospital.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.hospital.person.dto.GetPatientResponse;
import pl.edu.pg.student.hospital.person.dto.GetPatientsResponse;
import pl.edu.pg.student.hospital.person.dto.PostPatientRequest;
import pl.edu.pg.student.hospital.person.dto.PutPatientRequest;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.person.service.PatientService;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.service.WardService;

import java.util.Optional;

@RestController
@RequestMapping("api/wards/{name}/patients")
public class WardPatientController {
    private final PatientService patientService;
    private final WardService wardService;


    @Autowired
    public WardPatientController(PatientService patientService, WardService wardService) {
        this.patientService = patientService;
        this.wardService = wardService;
    }

    @GetMapping
    public ResponseEntity<GetPatientsResponse> getPatients(@PathVariable("name") String wardName) {
        return wardService.find(wardName)
                .map(ward -> ResponseEntity.ok(GetPatientsResponse.entityToDtoMapper().apply(patientService.findAll(ward))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{pesel}")
    public ResponseEntity<GetPatientResponse> getPatient(@PathVariable("name") String wardName, @PathVariable("pesel") String pesel) {
        return patientService.find(pesel, wardName)
                .map(patient -> ResponseEntity.ok(GetPatientResponse.entityToDtoMapper().apply(patient)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postPatient(@PathVariable("name") String wardName, @RequestBody PostPatientRequest request, UriComponentsBuilder builder) {
        Optional<Ward> ward = wardService.find(wardName);
        if(ward.isPresent()) {
            Patient patient = PostPatientRequest
                    .dtoToEntityMapper(ward::get)
                    .apply(request);
            patientService.save(patient);
            return ResponseEntity.created(builder.pathSegment("api", "users", "{name}", "patients", "{pesel}")
                    .buildAndExpand(ward.get().getName(), patient.getPesel()).toUri()).build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{pesel}")
    public ResponseEntity<Void> putPatient(@RequestBody PutPatientRequest request, @PathVariable("name") String wardName, @PathVariable("pesel") String pesel) {
        Optional<Patient> patient = patientService.find(pesel, wardName);
        if (patient.isPresent()) {
            PutPatientRequest.dtoToEntityMapper().apply(patient.get(), request);
            patientService.update(patient.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{pesel}")
    public ResponseEntity<Void> deletePatient(@PathVariable("name") String wardName, @PathVariable("pesel") String pesel) {
        Optional<Patient> patient = patientService.find(pesel, wardName);
        if (patient.isPresent()) {
            patientService.delete(patient.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
