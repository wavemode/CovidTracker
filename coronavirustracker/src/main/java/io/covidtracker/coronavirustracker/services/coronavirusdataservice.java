package io.covidtracker.coronavirustracker.services;

import io.covidtracker.coronavirustracker.models.Location;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
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
public class coronavirusdataservice {

    private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us-counties.csv";

    // create a list of class location
    private List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    @PostConstruct

    // execute the function every day to update data
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<Location> newLocation = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        // create a reader for the csv file
        StringReader csvBodyReader = new StringReader(httpResponse.body());

        // loop through to get header values
        // get column for each record
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            Location location = new Location();
            location.setState(record.get("state"));
            location.setCounty(record.get("county"));
            location.setCases(Integer.parseInt(record.get("cases")));
            //System.out.println(location);
            newLocation.add(location);
        }

        // this is to avoid concurrency problems
        this.locations = newLocation;
    }

}
