package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.CountryDto;
import oksana.dvornitska.entities.Country;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(uses = CountryMapper.class, config = CountryMapperConfig.class)
public interface CountryMapper {

    @InheritConfiguration
    CountryDto map(Country country);
}