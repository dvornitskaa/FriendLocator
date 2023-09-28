package oksana.dvornitska.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;

import oksana.dvornitska.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return ResponseEntity.ok("");
    }
    @PostMapping("makeFriend")//
    public ResponseEntity<String> friend(String userName, String friendName){
        return ResponseEntity.ok(userService.makeFriend(userName,friendName));
    }
    @GetMapping ("findFriendsByCountry")
    public ResponseEntity<List<UserDto>> findFriendsByCountry (String userName, String country){
        return ResponseEntity.ok(userService.findFriendsByCountry(userName,country));
    }
    @GetMapping("findFriends")
    public ResponseEntity<List<UserDto>> findFriends (String userName){
        return ResponseEntity.ok(userService.findAllFriendsLocation(userName));
    }
    @PostMapping("updateCountry")
    public ResponseEntity<String> updateCountry(String userName, String country, String city){
        return ResponseEntity.ok(userService.updateLocation(userName,country, city));
    }
    @GetMapping("statistics")
    public ResponseEntity<HashMap<String, Double>> statistics(){
        return ResponseEntity.ok(userService.getCountryStatistics());
    }
    @GetMapping("history")
    public ResponseEntity<List<LocationDto>> locationHistory(String userName){
        return ResponseEntity.ok(userService.locationHistory(userName));
    }
    @GetMapping("findUser")
    public ResponseEntity<List<UserDto>> findUser(String userName){
        return  ResponseEntity.ok(userService.findUser(userName));
    }

}
