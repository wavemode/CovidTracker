package CSC4410.CovidTracker;

import CSC4410.CovidTracker.counties.CountyData;
import CSC4410.CovidTracker.counties.CountyRepository;
import CSC4410.CovidTracker.state.StateData;
import CSC4410.CovidTracker.state.StateRepository;
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
public class DataCollector {
    private static String VIRUS_DATA_URI = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us-counties.csv";
    //create a list of county objects
    private List<CountyData> countyData = new ArrayList<>();

    @Autowired
    CountyRepository countyRepository;
   @Autowired
    StateRepository stateRepository;


    @PostConstruct
// execute the function every day to update data
    @Scheduled(cron = "* * * 1 * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<CountyData> newCountyData = new ArrayList<>();
        List<StateData> newStateData = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URI))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(httpResponse.body());
        // create a reader for the csv file
        StringReader csvReader = new StringReader(httpResponse.body());
// loop through to get header values
        // get column for each record
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            CountyData countyData = new CountyData();
            StateData stateData = new StateData();

            countyData.setStateData(stateData);
            countyData.getStateData().setState(record.get("state"));
            countyData.setCounty(record.get("county"));
            countyData.setDeaths(record.get("deaths"));
            countyData.setCases(Integer.parseInt(record.get("cases")));


            // save data  in the database;

           stateRepository.save(countyData.getStateData());
            countyRepository.save(countyData);

            newCountyData.add(countyData);
            newStateData.add(stateData);

        }
        //avoid concurrency problems
        this.countyData = newCountyData;

        countyData.parallelStream().forEach(System.out::println);

    }

    public List<CountyData> getLocations() {
        return countyData;
    }

}
