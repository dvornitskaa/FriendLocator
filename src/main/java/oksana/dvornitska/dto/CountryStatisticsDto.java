package oksana.dvornitska.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data //getter
@AllArgsConstructor
//@NoArgsConstructor
//@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryStatisticsDto {
    private String country;
    private long userCount;
    private double userPercentage;

}
