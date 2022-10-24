package pl.edu.pg.student.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.person.service.PatientService;
import pl.edu.pg.student.hospital.ward.service.WardService;

@Component
public class CommandLine implements CommandLineRunner {
    private PatientService patientService;
    private WardService wardService;

    @Autowired
    public CommandLine(PatientService patientService, WardService wardService) {
        this.patientService = patientService;
        this.wardService = wardService;
    }

    @Override
    public void run(String... args) throws Exception {
        patientService.findAll().forEach(System.out::println);
    }
}
