package oksana.dvornitska.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.services.interfaces.PostServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostController {
    @Autowired
    PostServiceI postService;
    @PostMapping
    public ResponseEntity<String> create(@RequestBody PostDto postDto){
        postService.addPost(postDto);
        return  ResponseEntity.ok("");
    }
    @GetMapping("feed")
    public ResponseEntity<List<PostDto>> feed(String username){
        return ResponseEntity.ok(postService.allFriendsPosts(username));
    }
}
