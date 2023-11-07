package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.mappers.UserMapper;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.PostRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.FriendServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendService implements FriendServiceI {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    PostRepository postRepository;
    @Override
    public String makeFriend(String userName, String friendName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        User userFriend = userRepository.findUserByName(friendName)
                .orElseThrow(()-> new UserNotFoundException(friendName + " does not exist"));

        user.getFriends().add(userFriend);
        userFriend.getFriends().add(user);
        userRepository.save(user);
        userRepository.save(userFriend);
        log.info(String.format("%s friend is added to %s", friendName,userName));
        return "friend is added";
    }
    @Override
    public List<UserDto> findFriendsByCountry(String userName, String country) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        return user.getFriends().stream().map(UserMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findAllFriendsLocation(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        List<UserDto> userDtos = new ArrayList<>();
        for (User friend : user.getFriends()) {
            UserDto userDto = UserMapper.INSTANCE.mapToDto(friend);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
