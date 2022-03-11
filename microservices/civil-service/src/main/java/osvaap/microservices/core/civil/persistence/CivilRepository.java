package osvaap.microservices.core.civil.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CivilRepository extends CrudRepository<CivilEntity, String> {
    Optional<CivilEntity> findByRFC(String RFC);
}
