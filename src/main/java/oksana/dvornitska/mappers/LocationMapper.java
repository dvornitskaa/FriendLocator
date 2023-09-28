package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.entities.Location;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = LocationMapper.class, config = LocationMapperConfig.class)
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    @InheritConfiguration
    LocationDto mapToDto(Location location);
    @InheritConfiguration
    Location mapToEntity(LocationDto locationDto);
}