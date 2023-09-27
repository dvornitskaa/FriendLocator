package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.CountryDto;
import oksana.dvornitska.entities.Country;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CountryMapper.class, config = CountryMapperConfig.class)
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    @InheritConfiguration
    CountryDto mapToDto(Country country);
}