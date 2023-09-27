package oksana.dvornitska.services.interfaces;


import oksana.dvornitska.dto.CountryDto;
import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Country;

import java.util.HashMap;
import java.util.List;

public interface UserServiceI {
    void addUser(UserDto userDto);
    String makeFriend(String userName, String friendName);
    String updateCountry(String userName, String country, String city);
    List<UserDto> findFriendsByCountry (String userName, String country);
    List<UserDto> findAllFriendsLocation (String userName);
    List<CountryDto> locationHistory (String userName);
    List<UserDto> findUser (String userName);
    HashMap<String, Double> getCountryStatistics();
    String addPost(PostDto postDto);
    List<PostDto> allFriendsPosts(String userName);

}
