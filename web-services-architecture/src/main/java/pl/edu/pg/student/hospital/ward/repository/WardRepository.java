package pl.edu.pg.student.hospital.ward.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.student.hospital.ward.entity.Ward;

@org.springframework.stereotype.Repository
public interface WardRepository extends JpaRepository<Ward, String> {
}
