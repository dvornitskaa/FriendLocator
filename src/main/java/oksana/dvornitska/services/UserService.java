package oksana.dvornitska.services;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Location;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.mappers.LocationMapper;
import oksana.dvornitska.mappers.UserMapper;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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
        user.getCountries().add(location);
        locationRepository.save(location);
        userRepository.save(user);
    }
    @Override
    public List<UserDto> findUser(String userName) {
        return userRepository.findUsersByNameStartsWith(userName).stream()
                .map(UserMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }




}
