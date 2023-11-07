package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Post;
import oksana.dvornitska.entities.User;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PostMapper.class, config = PostMapperConfig.class)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
    @InheritConfiguration
    PostDto mapToDto(Post post);
    @InheritConfiguration
    Post mapToEntity(PostDto postDto);
}
