package pl.edu.pg.student.hospital.ward.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pg.student.hospital.ward.entity.Ward;
import pl.edu.pg.student.hospital.ward.repository.WardRepository;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@Service
public class WardService {

    WardRepository wardRepository;

    @Autowired
    public WardService(WardRepository wardRepository) {
        this.wardRepository = wardRepository;
    }

    public Optional<Ward> find(String name) {
        return wardRepository.find(name);
    }

    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    public void save(Ward ward) {
        wardRepository.save(ward);
    }

    public void delete(Ward ward) {
        wardRepository.delete(ward);
    }
}