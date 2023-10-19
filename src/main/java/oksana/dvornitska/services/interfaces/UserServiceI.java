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


    List<UserDto> findUser (String userName);



}
