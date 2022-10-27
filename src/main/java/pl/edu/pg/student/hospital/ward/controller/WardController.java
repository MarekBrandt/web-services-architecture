package pl.edu.pg.student.hospital.ward.controller;

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
import pl.edu.pg.student.hospital.ward.service.WardService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/wards")
public class WardController {
    private final PatientService patientService;
    private final WardService wardService;


    @Autowired
    public WardController(PatientService patientService, WardService wardService) {
        this.patientService = patientService;
        this.wardService = wardService;
    }

    @GetMapping
    public ResponseEntity<GetPatientsResponse> getPatients() {
        List<Patient> patients = patientService.findAll();
        Function<Collection<Patient>, GetPatientsResponse> mapper = GetPatientsResponse.entityToDtoMapper();
        GetPatientsResponse response = mapper.apply(patients);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{pesel}")
    public ResponseEntity<GetPatientResponse> getPatient(@PathVariable("pesel") String pesel) {
        return patientService.find(pesel)
                .map(patient -> ResponseEntity.ok(GetPatientResponse.entityToDtoMapper().apply(patient)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postPatient(@RequestBody PostPatientRequest request, UriComponentsBuilder builder) {
        Patient patient = PostPatientRequest
                .dtoToEntityMapper(() -> wardService.find(request.getWard()).orElseThrow())
                .apply(request);
        patientService.save(patient);
        return ResponseEntity.created(builder.pathSegment("api", "patients", "{pesel}")
                .buildAndExpand(patient.getPesel()).toUri()).build();
    }

    @PutMapping("{pesel}")
    public ResponseEntity<Void> putPatient(@RequestBody PutPatientRequest request, @PathVariable("pesel") String pesel) {
        Optional<Patient> patient = patientService.find(pesel);
        if(patient.isPresent()) {
            PutPatientRequest.dtoToEntityMapper().apply(patient.get(), request);
            patientService.update(patient.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{pesel}")
    public ResponseEntity<Void> deletePatient(@PathVariable("pesel") String pesel) {
        Optional<Patient> patient = patientService.find(pesel);
        if(patient.isPresent()) {
            patientService.delete(patient.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
