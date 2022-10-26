package pl.edu.pg.student.hospital.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.person.entity.Name;
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
                    .numberOfBeds(10)
                    .areaInSquareMeters(60f)
                    .build();
            Ward generalWard = Ward.builder()
                    .name("general")
                    .numberOfBeds(20)
                    .areaInSquareMeters(100f)
                    .build();
            Ward causalityWard = Ward.builder()
                    .name("causality")
                    .numberOfBeds(8)
                    .areaInSquareMeters(54.5f)
                    .build();

            wardService.save(maternityWard);
            wardService.save(generalWard);
            wardService.save(causalityWard);

            Name name1 = new Name("Jan", "Kowalski");
            Name name2 = new Name("Andrzej", "Drwalski");
            Name name3 = new Name("Michał", "Mechaniczny");

            Patient kowalski = Patient.builder()
                    .pesel("0123")
                    .age(23)
                    .ward(maternityWard)
                    .name(name2)
                    .build();
            Patient drwalski = Patient.builder()
                    .pesel("4567")
                    .age(47)
                    .ward(generalWard)
                    .name(name1)
                    .build();
            Patient mechaniczny = Patient.builder()
                    .pesel("8910")
                    .age(33)
                    .ward(causalityWard)
                    .name(name3)
                    .build();

            patientService.save(kowalski);
            patientService.save(drwalski);
            patientService.save(mechaniczny);
        }
    }
}
