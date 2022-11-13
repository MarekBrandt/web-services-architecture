package pl.edu.pg.student.hospital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.person.entity.Patient;
import pl.edu.pg.student.hospital.person.service.PatientService;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.service.WardService;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private final PatientService patientService;
    private final WardService wardService;

    Scanner scanner;

    @Autowired
    public CommandLine(PatientService patientService, WardService wardService) {
        this.patientService = patientService;
        this.wardService = wardService;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean shouldRun = false;
        scanner = new Scanner(System.in);

        while (shouldRun) {
            showMenu();
            String userResponse = scanner.nextLine();

            switch (userResponse) {
                case "1":
                    showCategories();
                    break;
                case "2":
                    showAllElements();
                    break;
                case "3":
                    try {
                        addElement();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Adding element failed, try again");
                    }
                    break;
                case "4":
                    try {
                        deleteElement();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Deleting element failed, try again");
                    }
                    break;
                case "5":
                    shouldRun = false;
                    break;
                default:
                    System.out.println("Unexpected entry, try again");
            }
        }
        scanner.close();
    }

    private void showAllElements() {
        patientService.findAll().forEach(patient -> System.out.println(patient.toString()));
        wardService.findAll().forEach(ward -> System.out.println(ward.toString()));
    }

    private void showCategories() {
        patientService.findAll().stream().findAny().ifPresent(patient -> System.out.println(patient.getClass().getSimpleName()));
        wardService.findAll().stream().findAny().ifPresent(ward -> System.out.println(ward.getClass().getSimpleName()));
    }

    private void addElement() throws IllegalArgumentException {
        System.out.println("Choose category:");
        showCategories();
        String chosenCategory = scanner.nextLine().toLowerCase();
        switch (chosenCategory) {
            case "patient":
                System.out.println("First name: ");
                String firstName = scanner.nextLine();
                System.out.println("Last name: ");
                String lastName = scanner.nextLine();
                System.out.println("Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.println("Pesel: ");
                String pesel = scanner.nextLine();
                System.out.println("Ward name");
                String wardName = scanner.nextLine();

                if (wardService.find(wardName).isEmpty() || pesel.isEmpty()) {
                    throw new IllegalArgumentException();
                }

                patientService.save(Patient.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .age(age)
                        .pesel(pesel)
                        .ward(wardService.find(wardName).get())
                        .build());
                break;
            case "ward":
                System.out.println("Ward name: ");
                String ward = scanner.nextLine();
                System.out.println("Number of beds: ");
                int bedsNumber = Integer.parseInt(scanner.nextLine());
                System.out.println("Area of ward in m^2: ");
                float area = Float.parseFloat(scanner.nextLine());

                wardService.save(Ward.builder()
                        .name(ward)
                        .areaInSquareMeters(area)
                        .numberOfBeds(bedsNumber).build());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void deleteElement() {
        System.out.println("Choose category:");
        showCategories();
        String chosenCategory = scanner.nextLine().toLowerCase();
        switch (chosenCategory) {
            case "patient":
                System.out.println("Pesel: ");
                String pesel = scanner.nextLine();

                patientService.delete(patientService.find(pesel).orElseThrow(IllegalArgumentException::new));
                break;
            case "ward":
                System.out.println("Ward name: ");
                String ward = scanner.nextLine();

                wardService.delete(wardService.find(ward).orElseThrow(IllegalArgumentException::new));
                break;
            default:
                throw new IllegalArgumentException();
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
