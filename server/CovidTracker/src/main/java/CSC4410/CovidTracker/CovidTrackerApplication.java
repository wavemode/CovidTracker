package CSC4410.CovidTracker;

import CSC4410.CovidTracker.counties.CountyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// enable scheduling from the CoronaVirusData
@EnableScheduling
public class CovidTrackerApplication {


@Autowired
	CountyService countyService = new CountyService();

	public static void main(String[] args) {
		SpringApplication.run(CovidTrackerApplication.class, args);



	}



}
