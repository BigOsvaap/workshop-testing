package osvaap.microservices.core.civil.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import osvaap.microservices.core.civil.MongoDbTestContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class CivilEntityTest extends MongoDbTestContainer {

    @Autowired
    private CivilRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void createCivil() {

        var entityToSave = new CivilEntity("Oswaldo", "Vazquez", "ASDASDASDASD12E");
        repository.save(entityToSave);

        var entityFound = repository.findByRFC(entityToSave.getRFC()).get();

        assertEquals(entityToSave.getId(), entityFound.getId());
        assertEquals(entityToSave.getVersion(), entityFound.getVersion());
        assertEquals(entityToSave.getName(), entityFound.getName());
        assertEquals(entityToSave.getLastName(), entityFound.getLastName());
        assertEquals(entityToSave.getRFC(), entityFound.getRFC());

    }

    @Test
    void duplicate() {

        var entity1 = new CivilEntity("Oswaldo", "Vazquez", "ASDASDASDASD12E");
        var entity2 = new CivilEntity("Carlos", "Herrera", "ASDASDASDASD12E");

        assertThrows(DuplicateKeyException.class, () -> {
           repository.save(entity1);
           repository.save(entity2);
        });

    }

}