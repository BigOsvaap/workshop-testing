package osvaap.microservices.core.civil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import osvaap.microservices.core.civil.persistence.CivilEntity;

@SpringBootApplication
@ComponentScan("osvaap")
public class CivilServiceApplication {

	private static final Logger LOG = LoggerFactory.getLogger(CivilServiceApplication.class);

	private final MongoOperations mongoTemplate;

	@Autowired
	public CivilServiceApplication(MongoOperations mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(CivilServiceApplication.class, args);

		String mongoDbHost = ctx.getEnvironment().getProperty("spring.data.mongodb.host");
		String mongoDbPort = ctx.getEnvironment().getProperty("spring.data.mongodb.port");
		LOG.info("Connected to MongoDb: " + mongoDbHost + ":" + mongoDbPort);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void initIndicesAfterStartup() {
		MappingContext<? extends MongoPersistentEntity<?>, MongoPersistentProperty> mappingContext = mongoTemplate.getConverter().getMappingContext();
		IndexResolver resolver = new MongoPersistentEntityIndexResolver(mappingContext);

		IndexOperations indexOps = mongoTemplate.indexOps(CivilEntity.class);
		resolver.resolveIndexFor(CivilEntity.class).forEach(indexOps::ensureIndex);
	}

}
