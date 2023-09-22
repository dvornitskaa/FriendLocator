package oksana.dvornitska.services;

import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setCountry(userDto.getCountry());
        userRepository.save(user);
    }

    @Override
    public String makeFriend(String userName, String friendName) {
        Optional<User> user = userRepository.findUserByName(userName);
        Optional<User> userFriend = userRepository.findUserByName(friendName);
        if (user.isEmpty()){
            return "user does not exist";
        }
        if (userFriend.isEmpty()){
            return "friend does not exist";
        }
        user.get().getFriends().add(userFriend.get());
        userRepository.save(user.get());
        return "friend is added";
    }

    @Override
    public List<UserDto> findFriendsByCountry(String userName, String country) {
        Optional<User> user = userRepository.findUserByName(userName);
        if(user.isEmpty()){

        }
        return null;
    }
}