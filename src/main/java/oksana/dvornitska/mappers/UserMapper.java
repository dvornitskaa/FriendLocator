package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto mapToDto(User user);
    User mapToEntity(UserDto userDto);
}
