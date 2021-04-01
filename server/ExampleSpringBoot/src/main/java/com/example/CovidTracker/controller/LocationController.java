package com.example.CovidTracker.controller;


import com.example.CovidTracker.DataConnector;
import com.example.CovidTracker.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class LocationController {
    @Autowired
    DataConnector dataConnector;

    @GetMapping("/home")
    public String home(Model model) {
        List<Location> countyData = dataConnector.getLocations();
        int totalcases = countyData.stream().mapToInt(stat -> stat.getCases()).sum();

        model.addAttribute("locations", countyData);
        model.addAttribute("totalcases", totalcases);
        return "home";
    }
}
