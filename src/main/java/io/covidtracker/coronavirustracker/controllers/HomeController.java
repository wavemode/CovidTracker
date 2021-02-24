package io.covidtracker.coronavirustracker.controllers;

import io.covidtracker.coronavirustracker.models.Location;
import io.covidtracker.coronavirustracker.services.coronavirusdataservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    coronavirusdataservice coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<Location> locations = coronaVirusDataService.getStats();

        model.addAttribute("test", locations);

        return "home";
    }
}
