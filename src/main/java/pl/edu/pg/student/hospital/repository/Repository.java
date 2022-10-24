package pl.edu.pg.student.hospital.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<E, K> {
    Optional<E> find(K key) throws IllegalArgumentException;

    List<E> findAll();

    void save(E entity);

    void delete(E entity);
}
