package com.example.CovidTracker.LocationRestApi;

import com.example.CovidTracker.LocationModel.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RestApiController {

    @Autowired
    com.example.CovidTracker.LocationService.virusRepository virusRepository;

    @RequestMapping(path = "/api/location",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Location> getAllLocations(){

      return  virusRepository.findAll();


    }
    @RequestMapping(path = "/api/location/state/county/{county}",produces = MediaType.APPLICATION_JSON_VALUE)

    public Optional<Location> getLocation(@PathVariable("county") String county){

        Optional<Location> countyLocation =  virusRepository.findById(county);
        return countyLocation;

    }
    @RequestMapping(value = "/api/location/state/{state}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Location> getStateLocation(@PathVariable("state") String state){
        return virusRepository.findByStateIgnoreCase(state);

    }

}
