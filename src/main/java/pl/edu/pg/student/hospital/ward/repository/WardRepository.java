package pl.edu.pg.student.hospital.ward.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.student.hospital.datastore.DataStore;
import pl.edu.pg.student.hospital.repository.Repository;
import pl.edu.pg.student.hospital.ward.entity.Ward;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class WardRepository implements Repository<Ward, String> {

    private DataStore dataStore;

    @Autowired
    public WardRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Override
    public Optional<Ward> find(String name) throws IllegalArgumentException {
        return dataStore.findWard(name);
    }

    @Override
    public List<Ward> findAll() {
        return dataStore.findAllWards();
    }

    @Override
    public void save(Ward ward) {
        dataStore.saveWard(ward);
    }

    @Override
    public void delete(Ward ward) {
        dataStore.deleteWard(ward);
    }
}
