package CSC4410.CovidTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// enable scheduling from the CoronaVirusData
@EnableScheduling
public class CovidTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidTrackerApplication.class, args);
	}

}
