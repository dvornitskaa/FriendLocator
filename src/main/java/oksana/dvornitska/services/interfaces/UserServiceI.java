package oksana.dvornitska.services.interfaces;


import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Location;

import java.util.HashMap;
import java.util.List;

public interface UserServiceI {
    void addUser(UserDto userDto);
    String makeFriend(String userName, String friendName);
    String updateLocation(String userName, String country, String city);
    List<UserDto> findFriendsByCountry (String userName, String country);
    List<UserDto> findAllFriendsLocation (String userName);
    List<LocationDto> locationHistory (String userName);
    List<UserDto> findUser (String userName);
    HashMap<String, Double> getCountryStatistics();


}
