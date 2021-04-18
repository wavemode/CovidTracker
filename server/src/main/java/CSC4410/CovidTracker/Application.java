package CSC4410.CovidTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling // enable scheduling from the CoronaVirusData
public class Application {

	public static void main(String[] args) throws SQLException, IOException {

		// start the REST application
		SpringApplication.run(Application.class, args);

	}

}
