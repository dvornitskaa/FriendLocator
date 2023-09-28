package oksana.dvornitska.controllers;

import oksana.dvornitska.dto.CountryDto;
import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Country;
import oksana.dvornitska.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return ResponseEntity.ok("");
    }
    @PostMapping("makeFriend")
    public ResponseEntity<String> makeFriend(String userName, String friendName){
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
        return ResponseEntity.ok(userService.updateCountry(userName,country, city));
    }
    @GetMapping("statistics")
    public ResponseEntity<HashMap<String, Double>> statistics(){
        return ResponseEntity.ok(userService.getCountryStatistics());
    }
    @GetMapping("history")
    public ResponseEntity<List<CountryDto>> locationHistory(String userName){
        return ResponseEntity.ok(userService.locationHistory(userName));
    }
    @GetMapping("findUser")
    public ResponseEntity<List<UserDto>> findUser(String userName){
        return  ResponseEntity.ok(userService.findUser(userName));
    }
    @PostMapping("addPost")
    public ResponseEntity<String> addPost(@RequestBody PostDto postDto){
        userService.addPost(postDto);
        return  ResponseEntity.ok("");
    }
    @GetMapping("postFeed")
    public ResponseEntity<List<PostDto>> postFeed(String username){
        return ResponseEntity.ok(userService.allFriendsPosts(username));
    }
}
