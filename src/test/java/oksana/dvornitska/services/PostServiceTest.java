package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.entities.Location;
import oksana.dvornitska.entities.Post;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.PostRepository;
import oksana.dvornitska.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostServiceTest {
    @Autowired
    private PostService postService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private PostRepository postRepository;



    @Test
    public void testAddPost() {
        // Создаем моки для пользователя и местоположения
        User mockUser = new User();
        Location mockLocation = new Location();

        // Создаем объект PostDto
        PostDto postDto = new PostDto();
        postDto.setUsername("testUser");

        // Определяем поведение моков
        when(userRepository.findUserByName("testUser")).thenReturn(Optional.of(mockUser));
        when(locationRepository.save(any(Location.class))).thenReturn(mockLocation);

        // Вызываем метод
        String result = postService.addPost(postDto);

        // Проверяем, что методы были вызваны ожидаемое количество раз
        verify(userRepository, times(1)).findUserByName("testUser");
        verify(locationRepository, times(1)).save(mockLocation);
        verify(userRepository, times(1)).save(mockUser);
        verify(postRepository, times(1)).save(any(Post.class));

        // Проверяем, что результат соответствует ожиданиям
        assertEquals("post added", result);
    }

    @Test
    public void testAllPosts() {
        // Создаем мок для пользователя
        User mockUser = new User();
        mockUser.setName("testUser");

        // Создаем список друзей и постов
        User friend1 = new User();
        User friend2 = new User();
        Post post1 = new Post();
        post1.setPrivateCheck(true);  // Частный пост
        Post post2 = new Post();
        post2.setPrivateCheck(false);  // Публичный пост

        List<User> friends = new ArrayList<>();
        friends.add(friend1);
        friends.add(friend2);

        mockUser.setFriends(friends);

        // Определяем поведение моков
        when(userRepository.findUserByName("testUser")).thenReturn(Optional.of(mockUser));
        when(postRepository.findAll()).thenReturn(List.of(post1, post2));

        // Вызываем метод
        List<PostDto> result = postService.allPosts("testUser");

        // Проверяем результат
        assertEquals(2, result.size());  // Проверяем, что в результате два поста

        // Здесь вы можете дополнительно проверить, что ожидаемые посты находятся в результате
        // Например, вы можете сравнить посты post1 и post2 с соответствующими элементами в result.

        // Пример для дополнительной проверки:
        // assertEquals(post1.getId(), result.get(0).getId());
        // assertEquals(post2.getId(), result.get(1).getId());
    }
}
