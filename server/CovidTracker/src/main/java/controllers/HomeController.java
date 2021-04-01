package controllers;

import CSC4410.CovidTracker.CoronaVirusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    CoronaVirusData corona;

@GetMapping("/")
    public String home(Model model){
    model.addAttribute("locations",corona.getLocations() );
    return "home";
    }
}
