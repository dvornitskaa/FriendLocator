package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.FriendServiceI;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendServiceTest {
    @Autowired
    FriendServiceI friendService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    LocationRepository locationRepository;
    @Test
    void testMakeFriend(){
        Mockito.when(userRepository.findUserByName(any())).thenReturn(Optional.of(new User()));
        String result = friendService.makeFriend("TestName1", "TestName2");

        verify(userRepository).findUserByName("TestName1");
        verify(userRepository).findUserByName("TestName2");
        verify(userRepository, times(2)).save(any());
        assertEquals("friend is added", result);
    }
    @Test
    void testMakeFriendWithNonExistentUser() {
        Mockito.when(userRepository.findUserByName("NonExistentUser")).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            friendService.makeFriend("TestName1", "NonExistentUser");
        });
    }
    @Test
    void testFindFriendsByCountry() {
        User user = new User();
        user.setName("TestName");
        User friend1 = new User();
        friend1.setName("Friend1");
        User friend2 = new User();
        friend2.setName("Friend2");
        user.getFriends().add(friend1);
        user.getFriends().add(friend2);

        Mockito.when(userRepository.findUserByName("TestName")).thenReturn(Optional.of(user));

        List<UserDto> result = friendService.findFriendsByCountry("TestName", "CountryName");

        assertEquals(2, result.size());
    }
    @Test
    void testFindAllFriendsLocation() {
        User user = new User();
        user.setName("TestName");
        User friend1 = new User();
        friend1.setName("Friend1");
        User friend2 = new User();
        friend2.setName("Friend2");
        user.getFriends().add(friend1);
        user.getFriends().add(friend2);

        Mockito.when(userRepository.findUserByName("TestName")).thenReturn(Optional.of(user));

        List<UserDto> result = friendService.findAllFriendsLocation("TestName");

        assertEquals(2, result.size());
    }
}
