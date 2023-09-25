package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.CountryDto;
import oksana.dvornitska.entities.Country;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@MapperConfig
public interface CountryMapperConfig {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "city", source = "city"),

    })
    CountryDto mapToDto(Country country);
}
