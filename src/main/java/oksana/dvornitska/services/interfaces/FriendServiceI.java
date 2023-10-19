package oksana.dvornitska.services.interfaces;

import oksana.dvornitska.dto.UserDto;

import java.util.List;

public interface FriendServiceI {
    String makeFriend(String userName, String friendName);
    List<UserDto> findFriendsByCountry (String userName, String country);
    List<UserDto> findAllFriendsLocation (String userName);
}
