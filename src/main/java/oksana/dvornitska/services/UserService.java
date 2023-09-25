package oksana.dvornitska.services;

import oksana.dvornitska.dto.CountryStatisticsDto;
import oksana.dvornitska.dto.PostDto;
import oksana.dvornitska.dto.UserDto;
import oksana.dvornitska.entities.Country;
import oksana.dvornitska.entities.Post;
import oksana.dvornitska.entities.User;
import oksana.dvornitska.exceptions.UserNotFoundException;
import oksana.dvornitska.mappers.UserMapper;
import oksana.dvornitska.repositories.CountryRepository;
import oksana.dvornitska.repositories.PostRepository;
import oksana.dvornitska.repositories.UserRepository;
import oksana.dvornitska.services.interfaces.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserServiceI {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void addUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapToEntity(userDto);
        Country country = new Country();
        country.setName(userDto.getCountry());
//        user.setName(userDto.getName());
//        user.setCountry(userDto.getCountry());
//        user.getCountries().add(country);
        countryRepository.save(country);
        userRepository.save(user);
    }

    @Override
    public String makeFriend(String userName, String friendName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        User userFriend = userRepository.findUserByName(friendName)
                .orElseThrow(()-> new UserNotFoundException(friendName + " does not exist"));

        user.getFriends().add(userFriend);
        userFriend.getFriends().add(user);
        userRepository.save(user);
        return "friend is added";
    }

    @Override
    public String updateCountry(String userName, String country, String city) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        user.setCountry(country);
        user.setCity(city);
        Country countryEntity = new Country();
        countryEntity.setName(country);
        user.getCountries().add(countryEntity);
        countryRepository.save(countryEntity);
        userRepository.save(user);
        return "country updated";
    }

    @Override
    public List<UserDto> findFriendsByCountry(String userName, String country) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        List<UserDto> userDtos = new ArrayList<>();
        for (User friend : user.getFriends()) {
            if (friend.getCountry().equals(country)) {
                UserDto userDto = new UserDto();
                //UserDto userDto = UserMapper.INSTANCE.mapToDto(user);//????
                userDto.setId(friend.getId());
                userDto.setName(friend.getName());
                userDtos.add(userDto);
            }
        }
        return userDtos;
    }

    @Override
    public List<UserDto> findAllFriendsLocation(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));
        List<UserDto> userDtos = new ArrayList<>();
        for (User friend : user.getFriends()) {
            UserDto userDto = UserMapper.INSTANCE.mapToDto(friend);
//            userDto.setId(friend.getId());
//            userDto.setName(friend.getName());
//            userDto.setCountry(friend.getCountry());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    @Override
    public List<Country> locationHistory(String userName) {
        User user = userRepository.findUserByName(userName)
                .orElseThrow(()-> new UserNotFoundException(userName + " does not exist"));

        return user.getCountries();
    }

    @Override
    public List<UserDto> findUser(String userName) {
        return userRepository.findUsersByNameStartsWith(userName).stream()
                .map(UserMapper.INSTANCE::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public HashMap<String, Double> getCountryStatistics() {
        return (HashMap<String, Double>) userRepository.getCountryStatisticsAsMap();

    }

    @Override
    public String addPost(PostDto postDto) {
        User user = userRepository.findUserByName(postDto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(postDto.getUsername() + " does not exist"));
        Post post = new Post();

        post.setText(postDto.getText());
        post.setPlannedDate(postDto.getPlannedDate());
        Country country = new Country();
        country.setName(postDto.getCountry());
        country.setCity(postDto.getCity());
        post.setCountry(country);
        post.setUser(user);
        countryRepository.save(country);
        postRepository.save(post);
        return "post added";
    }
}
