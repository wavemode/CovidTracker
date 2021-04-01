package com.example.CovidTracker;

import com.example.CovidTracker.model.Location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataConnector {
    private static String VIRUS_DATA_URI = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us-counties.csv";
    // create a list of class location
    private List<Location> locations = new ArrayList<>();
    @Autowired
    com.example.CovidTracker.virusRepository virusRepository;

    @PostConstruct
// execute the function every day to update data
    @Scheduled(cron = "* * * 1 * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<Location> newLocation = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URI))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvReader = new StringReader(httpResponse.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            Location location = new Location();
            location.setState(record.get("state"));
            location.setCounty(record.get("county"));
            location.setDeaths(record.get("deaths"));
            location.setCases(Integer.parseInt(record.get("cases")));
            // use repository to store all data in the database
            virusRepository.save(location);
           // System.out.println(location.toString());
            newLocation.add(location);

        }
        // this is to avoid concurrency problems
        this.locations = newLocation;

    }

    public List<Location> getLocations() {
        return locations;
    }
}
