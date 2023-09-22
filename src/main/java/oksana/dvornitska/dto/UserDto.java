package oksana.dvornitska.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data //getter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Integer id;
    String name;
    String country;


    List<UserDto> friends;

    public UserDto(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
