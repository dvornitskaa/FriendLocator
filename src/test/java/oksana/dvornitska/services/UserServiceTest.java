package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceTest {
    @Autowired
    UserServiceI userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    LocationRepository locationRepository;
    @Test
    void testAddUser(){
       userService.addUser(new UserDto());

       verify(userRepository).save(any());
       verify(locationRepository).save(any());
    }
    @Test
    void testMakeFriend(){
        Mockito.when(userRepository.findUserByName(any())).thenReturn(Optional.of(new User()));
        String result = userService.makeFriend("TestName1", "TestName2");

        verify(userRepository).findUserByName("TestName1");
        verify(userRepository).findUserByName("TestName2");
        verify(userRepository, times(2)).save(any());
        assertEquals("friend is added", result);
    }
}
