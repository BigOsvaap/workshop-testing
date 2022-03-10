package osvaap.microservices.core.civil.services;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import osvaap.api.core.Civil;
import osvaap.api.core.CivilService;
import osvaap.api.exceptions.InvalidInputException;
import osvaap.api.exceptions.NotFoundException;
import osvaap.microservices.core.civil.persistence.CivilRepository;


@RestController
public class CivilServiceImpl implements CivilService {

    private final CivilRepository repository;
    private final CivilMapper mapper;

    @Autowired
    public CivilServiceImpl(CivilRepository repository, CivilMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Civil registerCivil(Civil body) {
        try {
            var entity = mapper.apiToEntity(body);
            var newCivil = repository.save(entity);
            return mapper.entityToApi(newCivil);
        } catch (DuplicateKeyException ex) {
            throw new InvalidInputException("Duplicate key, RFC: " + body.getRFC());
        }
    }

    @Override
    public Civil getCivil(String RFC) {
        if (RFC.length() < 13) {
            throw new InvalidInputException("Invalid RFC: " + RFC);
        }

        var entity = repository.findByRFC(RFC)
                .orElseThrow(() -> new NotFoundException("No civil found for RFC: " + RFC));

        return mapper.entityToApi(entity);
    }

    @Override
    public void deleteCivil(String RFC) {
        repository.findByRFC(RFC).ifPresent(repository::delete);
    }

}
