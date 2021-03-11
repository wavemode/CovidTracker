package com.example.CovidTracker.API;

import com.example.CovidTracker.DataConnector;
import com.example.CovidTracker.model.Location;
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
    DataConnector corona;
    @Autowired
    com.example.CovidTracker.virusRepository virusRepository;

    @RequestMapping(path = "/api/location",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Location> home(){

      return  virusRepository.findAll();


    }
    @RequestMapping(path = "/api/location/{county}",produces = MediaType.APPLICATION_XML_VALUE)
    public Optional<Location> getLocation(@PathVariable("county") String county){
return  virusRepository.findById(county);
    }
}
