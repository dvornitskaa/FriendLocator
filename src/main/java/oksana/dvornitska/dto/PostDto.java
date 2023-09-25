package oksana.dvornitska.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Data //getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {
    Integer id;
    String text;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    Date plannedDate;
    String username;
    String country;
    String city;
}
