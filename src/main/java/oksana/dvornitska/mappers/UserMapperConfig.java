package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@MapperConfig
public interface UserMapperConfig {

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),

    })
    UserDto mapToDto(User user);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "name", source = "name"),
            @Mapping(target = "country", source = "country"),
            @Mapping(target = "city", source = "city"),

    })
    User mapToEntity(UserDto userDto);
}
