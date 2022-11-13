package pl.edu.pg.student.hospital.initialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.service.WardService;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private final WardService wardService;

    @Autowired
    public DataInitializer(WardService wardService) {
        this.wardService = wardService;
    }

    @PostConstruct
    private synchronized void init() {
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
    }
}
