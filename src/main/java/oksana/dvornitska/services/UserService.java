package oksana.dvornitska.services;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Location;
import oksana.dvornitska.entities.Post;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.mappers.LocationMapper;
import oksana.dvornitska.mappers.PostMapper;
import oksana.dvornitska.mappers.UserMapper;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.PostRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements UserServiceI {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;


    @Override
    public void addUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapToEntity(userDto);
        Location location = new Location();
        location.setCountry(userDto.getCountry());
        location.setCity(userDto.getCity());

        locationRepository.save(location);
        userRepository.save(user);
    }

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
    public String updateLocation(String userName, String country, String city) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        user.setCountry(country);
        user.setCity(city);
        Location locationEntity = new Location();
        locationEntity.setCountry(country);
        locationEntity.setCity(city);
        user.getCountries().add(locationEntity);
        locationRepository.save(locationEntity);
        userRepository.save(user);
        return "location updated";
    }

    @Override
    public List<UserDto> findFriendsByCountry(String userName, String country) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        List<UserDto> userDtos = new ArrayList<>();
        for (User friend : user.getFriends()) { ///stream TODO
            if (friend.getCountry().equals(country)) {
                UserDto userDto = new UserDto();
                //UserDto userDto = UserMapper.INSTANCE.mapToDto(user);//????
                userDto.setId(friend.getId());
                userDto.setName(friend.getName());
                userDtos.add(userDto);
            }
        }
        return userDtos;
    }

    @Override
    public List<UserDto> findAllFriendsLocation(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        List<UserDto> userDtos = new ArrayList<>();
        for (User friend : user.getFriends()) {
            UserDto userDto = UserMapper.INSTANCE.mapToDto(friend);
//            userDto.setId(friend.getId());
//            userDto.setName(friend.getName());
//            userDto.setCountry(friend.getCountry());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public List<LocationDto> locationHistory(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));

        return user.getCountries().stream()
                .map(LocationMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUser(String userName) {
        return userRepository.findUsersByNameStartsWith(userName).stream()
                .map(UserMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HashMap<String, Double> getCountryStatistics() {
        return (HashMap<String, Double>) userRepository.getCountryStatisticsAsMap();

    }


}
