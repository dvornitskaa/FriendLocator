package oksana.dvornitska.services;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.entities.Location;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.mappers.LocationMapper;
import oksana.dvornitska.repositories.LocationRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.LocationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationService implements LocationServiceI {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LocationRepository locationRepository;
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
    public List<LocationDto> locationHistory(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));

        return user.getCountries().stream()
                .map(LocationMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }
    @Override
    public HashMap<String, Double> getCountryStatistics() {
        return (HashMap<String, Double>) userRepository.getCountryStatisticsAsMap();

    }
}
