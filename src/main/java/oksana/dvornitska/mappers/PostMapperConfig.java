package oksana.dvornitska.mappers;

import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.entities.Post;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
@MapperConfig
public interface PostMapperConfig {
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "text", source = "text"),
            @Mapping(target = "plannedDate", source = "plannedDate"),
            @Mapping(target = "privateCheck", source = "privateCheck"),
            @Mapping(target = "username", source = "user.name"),
            @Mapping(target = "country", source = "location.country"),
            @Mapping(target = "city", source = "location.city"),

    })
    PostDto mapToDto(Post post);
    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "text", source = "text"),
            @Mapping(target = "plannedDate", source = "plannedDate"),
            @Mapping(target = "privateCheck", source = "privateCheck"),
            @Mapping(target = "location.country", source = "country"),
            @Mapping(target = "location.city", source = "city"),

    })
    Post mapToEntity(PostDto postDto);
}
