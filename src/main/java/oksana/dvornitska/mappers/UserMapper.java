package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class, config = UserMapperConfig.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @InheritConfiguration
    UserDto mapToDto(User user);
    @InheritConfiguration
    User mapToEntity(UserDto userDto);
}
