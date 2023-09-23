package oksana.dvornitska.controllers;

import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> updateCountry(String userName, String country){
        return ResponseEntity.ok(userService.updateCountry(userName,country));
    }
}
