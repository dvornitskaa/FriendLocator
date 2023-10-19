package oksana.dvornitska.services.interfaces;

import oksana.dvornitska.dto.LocationDto;

import java.util.HashMap;
import java.util.List;

public interface LocationServiceI {
    String updateLocation(String userName, String country, String city);

    List<LocationDto> locationHistory (String userName);
    HashMap<String, Double> getCountryStatistics();
}
