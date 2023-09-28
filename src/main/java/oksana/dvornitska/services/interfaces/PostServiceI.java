package oksana.dvornitska.services.interfaces;

import oksana.dvornitska.dto.PostDto;

import java.util.List;

public interface PostServiceI {
    String addPost(PostDto postDto);
    List<PostDto> allFriendsPosts(String userName);
}
