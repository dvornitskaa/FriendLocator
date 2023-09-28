package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.entities.Location;
import oksana.dvornitska.entities.Post;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.mappers.PostMapper;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.PostRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.PostServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostService implements PostServiceI {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public String addPost(PostDto postDto) {
        User user = userRepository.findUserByName(postDto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(postDto.getUsername() + " does not exist"));
        Post post = new Post();

        post.setText(postDto.getText());
        post.setPlannedDate(postDto.getPlannedDate());
        Location location = new Location();
        location.setCountry(postDto.getCountry());
        location.setCity(postDto.getCity());
        post.setLocation(location);
        post.setUser(user);
        locationRepository.save(location);
        postRepository.save(post);
        return "post added";
    }

    @Override
    public List<PostDto> allFriendsPosts(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));

        return user.getFriends().stream()
                .flatMap(friend -> friend.getPosts().stream()) // "Разворачиваем" вложенные списки Post
                .map(PostMapper.INSTANCE::mapToDto) // Применяем маппинг к каждому Post
                .collect(Collectors.toList());

    }
}
