package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.model.CountyPopulation;
import CSC4410.CovidTracker.operation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Application.main is the starting point of the application. This will connect
 * to the database, initialize table(s) if needed, then start the Spring
 * application.
 */
@SpringBootApplication
@EnableScheduling // enable scheduling from the CoronaVirusData
public class Application {

	public static void main(String[] args) throws SQLException, IOException {

		// start the REST application
		SpringApplication.run(Application.class, args);

	}

}
