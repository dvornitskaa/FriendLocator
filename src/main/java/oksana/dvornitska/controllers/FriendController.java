package oksana.dvornitska.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.services.interfaces.FriendServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/friend")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FriendController {
    @Autowired
    FriendServiceI friendService;
    @PostMapping
    public ResponseEntity<String> friend(String userName, String friendName){
        return ResponseEntity.ok(friendService.makeFriend(userName,friendName));
    }
    @GetMapping("findFriendsByCountry")
    public ResponseEntity<List<UserDto>> findFriendsByCountry (String userName, String country){
        return ResponseEntity.ok(friendService.findFriendsByCountry(userName,country));
    }
    @GetMapping("findFriends")
    public ResponseEntity<List<UserDto>> findFriends (String userName){
        return ResponseEntity.ok(friendService.findAllFriendsLocation(userName));
    }
}
