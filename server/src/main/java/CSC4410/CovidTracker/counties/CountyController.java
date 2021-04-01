package CSC4410.CovidTracker.counties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class CountyController {
    @Autowired
    CountyService countyService;

    @GetMapping("/")
    public String home(Model model) {
        List<CountyData> countyData = countyService.getLocations();
        int totalcases = countyData.stream().mapToInt(stat -> stat.getCases()).sum();

        model.addAttribute("test", countyData);
        model.addAttribute("totalcases", totalcases);
        return "home";
    }
}