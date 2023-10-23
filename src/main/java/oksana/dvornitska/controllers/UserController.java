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

    @GetMapping
    public String hello (){
        return "hello";
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserDto userDto){
        userService.addUser(userDto);
        return ResponseEntity.ok("");
    }


    @GetMapping("findUser")
    public ResponseEntity<List<UserDto>> findUser(String userName){
        return  ResponseEntity.ok(userService.findUser(userName));
    }

}
