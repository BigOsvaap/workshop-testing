package osvaap.microservices.core.civil;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;

public abstract class MongoDbTestContainer {

    private static final MongoDBContainer container;

    static {
        container = new MongoDBContainer("mongo:4.4.2");
        container.start();
    }

    @DynamicPropertySource
    static void modify(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.host", container::getHost);
        registry.add("spring.data.mongodb.port", () -> container.getMappedPort(27017));
        registry.add("spring.data.mongodb.database", () -> "test");
    }

}
