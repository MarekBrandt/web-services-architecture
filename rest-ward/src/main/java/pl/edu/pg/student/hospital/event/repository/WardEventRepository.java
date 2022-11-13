package pl.edu.pg.student.hospital.event.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pl.edu.pg.student.hospital.event.dto.PostWardRequest;
import pl.edu.pg.student.hospital.ward.entity.Ward;

@Repository
public class WardEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public WardEventRepository(@Value("${hospital.patients.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Ward ward) {
        restTemplate.delete("/wards/{name}", ward.getName());
    }

    public void create(Ward ward) {
        restTemplate.postForLocation("/wards", PostWardRequest.entityToDtoMapper().apply(ward));
    }
}
