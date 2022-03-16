package osvaap.microservices.core.civil;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import osvaap.microservices.core.civil.persistence.CivilEntity;
import osvaap.microservices.core.civil.persistence.CivilRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CivilServiceApplicationTests extends MongoDbTestContainer{

    @Autowired
    private WebTestClient client;

    @Autowired
    private CivilRepository repository;

    private CivilEntity savedEntity;

    @BeforeEach
    void setup() {
        repository.deleteAll();
        savedEntity = repository.save(new CivilEntity("Oswaldo", "Vazquez", "ADASHDKASHDJASHD1"));
    }

    @Test
    void getCivil() {
        client.get().uri("/civil/" + savedEntity.getRFC())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody().jsonPath("$.rfc").isEqualTo(savedEntity.getRFC());
    }


}
