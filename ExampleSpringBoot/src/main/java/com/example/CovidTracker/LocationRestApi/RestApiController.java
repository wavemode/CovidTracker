package com.example.CovidTracker.LocationRestApi;

import com.example.CovidTracker.LocationModel.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {

    @Autowired
    com.example.CovidTracker.LocationService.virusRepository virusRepository;

    @GetMapping(path = "/api/location",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Location> getAllLocations(){

      return  virusRepository.findAll();


    }
    @GetMapping (path = "/api/location/state/county/{county}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody

    public Optional<Location> getLocation(@PathVariable("county") String county){

        Optional<Location> countyLocation =  virusRepository.findById(county);
        return countyLocation;

    }
    @PostMapping(path = "/api/location/save",produces = MediaType.APPLICATION_JSON_VALUE)
    public Location saveLocation(@RequestBody Location location){
       Location savedLocation =  virusRepository.save(location);
        return savedLocation;

    }
    @GetMapping (path = "/api/location/state/county/delete/{county}",produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteLocation(@PathVariable String county){
        virusRepository.deleteById(county);
    }


}
