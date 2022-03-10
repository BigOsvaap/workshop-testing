package osvaap.microservices.core.civil.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import osvaap.api.core.Civil;
import osvaap.microservices.core.civil.persistence.CivilEntity;

@Mapper(componentModel = "spring")
public interface CivilMapper {

    Civil entityToApi(CivilEntity entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    CivilEntity apiToEntity(Civil api);

}
