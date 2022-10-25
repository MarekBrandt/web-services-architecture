package pl.edu.pg.student.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.person.service.PatientService;
import pl.edu.pg.student.hospital.ward.service.WardService;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private final PatientService patientService;
    private final WardService wardService;

    @Autowired
    public CommandLine(PatientService patientService, WardService wardService) {
        this.patientService = patientService;
        this.wardService = wardService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean shouldRun = true;
        Scanner scanner = new Scanner(System.in);

        while (shouldRun) {
            showMenu();
            String userResponse = scanner.nextLine();
            
            switch (userResponse) {
                case "1":
                    patientService.findAll().stream().findAny().ifPresent(patient -> {
                        String patientClass = patient.getClass().getName();
                        System.out.println(patientClass.substring(patientClass.lastIndexOf(".")+1));
                    });
                    wardService.findAll().stream().findAny().ifPresent(ward -> System.out.println(ward.getClass()));
                    break;
                case "2":
                    patientService.findAll().forEach(patient -> System.out.println(patient.toString()));
                    wardService.findAll().forEach(ward -> System.out.println(ward.toString()));
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    shouldRun = false;
                    break;
                default:
                    System.out.println("Unexpected entry, try again");
            }
        }
    }

    private void showMenu() {
        System.out.println("Choose action:");
        System.out.println("\t1. List all categories");
        System.out.println("\t2. List all elements");
        System.out.println("\t3. Add element");
        System.out.println("\t4. Delete element");
        System.out.println("\t5. Stop application");
    }
}
