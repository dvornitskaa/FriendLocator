package oksana.dvornitska.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oksana.dvornitska.dto.LocationDto;
import oksana.dvornitska.services.interfaces.LocationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/v1/location")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationController {
    @Autowired
    LocationServiceI locationService;
    @PostMapping("updateCountry")
    public ResponseEntity<String> updateCountry(String userName, String country, String city){
        return ResponseEntity.ok(locationService.updateLocation(userName,country, city));
    }
    @GetMapping("statistics")
    public ResponseEntity<HashMap<String, Double>> statistics(){
        return ResponseEntity.ok(locationService.getCountryStatistics());
    }
    @GetMapping("history")
    public ResponseEntity<List<LocationDto>> locationHistory(String userName){
        return ResponseEntity.ok(locationService.locationHistory(userName));
    }
}
