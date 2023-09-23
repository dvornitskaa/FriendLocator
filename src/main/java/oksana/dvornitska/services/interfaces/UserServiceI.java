package oksana.dvornitska.services.interfaces;


import oksana.dvornitska.dto.UserDto;

import java.util.List;

public interface UserServiceI {
    void addUser(UserDto userDto);
    String makeFriend(String userName, String friendName);
    String updateCountry(String userName, String country);
    List<UserDto> findFriendsByCountry (String userName, String country);
    List<UserDto> findAllFriendsLocation (String userName);

}
