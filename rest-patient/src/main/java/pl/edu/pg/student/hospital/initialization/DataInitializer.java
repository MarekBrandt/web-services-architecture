package pl.edu.pg.student.hospital.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.person.service.PatientService;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.service.WardService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final PatientService patientService;
    private final WardService wardService;

    @Autowired
    public DataInitializer(PatientService patientService, WardService wardService) {
        this.patientService = patientService;
        this.wardService = wardService;
    }

    @PostConstruct
    private synchronized void init() {
        if (patientService.find("0123").isEmpty()) {
            Ward maternityWard = Ward.builder()
                    .name("maternity")
                    .build();
            Ward generalWard = Ward.builder()
                    .name("general")
                    .build();
            Ward causalityWard = Ward.builder()
                    .name("causality")
                    .build();

            wardService.save(maternityWard);
            wardService.save(generalWard);
            wardService.save(causalityWard);

            Patient kowalski = Patient.builder()
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .pesel("0123")
                    .age(23)
                    .ward(maternityWard)
                    .build();
            Patient drwalski = Patient.builder()
                    .firstName("Michał")
                    .lastName("Mechaniczny")
                    .pesel("4567")
                    .age(47)
                    .ward(generalWard)
                    .build();
            Patient mechaniczny = Patient.builder()
                    .firstName("Andrzej")
                    .lastName("Drwalski")
                    .pesel("8910")
                    .age(33)
                    .ward(causalityWard)
                    .build();

            patientService.save(kowalski);
            patientService.save(drwalski);
            patientService.save(mechaniczny);
        }
    }
}
