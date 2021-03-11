package CSC4410.CovidTracker.counties;

import CSC4410.CovidTracker.DataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller

public class CountyController {
    @Autowired
    DataCollector dataCollector;

    @GetMapping("/")
    public String home(Model model) {
        List<CountyData> countyData = dataCollector.getLocations();
        int totalcases = countyData.stream().mapToInt(stat -> stat.getCases()).sum();

        model.addAttribute("countyData", countyData);
        model.addAttribute("totalcases", totalcases);
        return "home";
    }
}