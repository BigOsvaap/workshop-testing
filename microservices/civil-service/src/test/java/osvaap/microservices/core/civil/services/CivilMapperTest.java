package osvaap.microservices.core.civil.services;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import osvaap.api.core.Civil;

import static org.junit.jupiter.api.Assertions.*;

class CivilMapperTest {

    private final CivilMapper mapper = Mappers.getMapper(CivilMapper.class);

    @Test
    void mapperTest() {
        assertNotNull(mapper);

        var api = new Civil("Oswaldo", "Vazquez", "QEWASDADASDASDAS23");
        var entity = mapper.apiToEntity(api);

        assertEquals(api.getName(), entity.getName());
        assertEquals(api.getLastName(), entity.getLastName());
        assertEquals(api.getRFC(), entity.getRFC());

        var api2 = mapper.entityToApi(entity);

        assertEquals(entity.getName(), api2.getName());
        assertEquals(entity.getLastName(), api2.getLastName());
        assertEquals(entity.getRFC(), api2.getRFC());

    }

}