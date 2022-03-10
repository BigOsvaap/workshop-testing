package osvaap.microservices.core.civil.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CivilRepository extends PagingAndSortingRepository<CivilEntity, String> {
    Optional<CivilEntity> findByRFC(String RFC);
}
