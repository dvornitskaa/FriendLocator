package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.entities.Location;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@MapperConfig
public interface LocationMapperConfig {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),

    })
    LocationDto mapToDto(Location location);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),

    })
    Location mapToEntity(LocationDto locationDto);

}
