package pl.edu.pg.student.hospital.ward.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.student.hospital.ward.dto.GetWardResponse;
import pl.edu.pg.student.hospital.ward.dto.GetWardsResponse;
import pl.edu.pg.student.hospital.ward.dto.PostWardRequest;
import pl.edu.pg.student.hospital.ward.dto.PutWardRequest;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.service.WardService;

import java.util.Optional;

@RestController
@RequestMapping("api/wards")
public class WardController {
    private final WardService wardService;


    @Autowired
    public WardController(WardService wardService) {
        this.wardService = wardService;
    }

    @GetMapping
    public ResponseEntity<GetWardsResponse> getWards() {
        GetWardsResponse response = GetWardsResponse.entityToDtoMapper().apply(wardService.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{name}")
    public ResponseEntity<GetWardResponse> getWard(@PathVariable("name") String name) {
        return wardService.find(name)
                .map(ward -> ResponseEntity.ok(GetWardResponse.entityToDtoMapper().apply(ward)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postWard(@RequestBody PostWardRequest request, UriComponentsBuilder builder) {
        Ward ward = PostWardRequest
                .dtoToEntityMapper()
                .apply(request);
        wardService.save(ward);
        return ResponseEntity.created(builder.pathSegment("api", "wards", "{name}")
                .buildAndExpand(ward.getName()).toUri()).build();
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> putWard(@RequestBody PutWardRequest request, @PathVariable("name") String name) {
        Optional<Ward> ward = wardService.find(name);
        if(ward.isPresent()) {
            PutWardRequest.dtoToEntityMapper().apply(ward.get(), request);
            wardService.update(ward.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> deleteWard(@PathVariable("name") String name) {
        Optional<Ward> ward = wardService.find(name);
        if(ward.isPresent()) {
            wardService.delete(ward.get());
            return ResponseEntity.accepted().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
