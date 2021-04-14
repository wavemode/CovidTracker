package CSC4410.CovidTracker.counties;

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
class CountyView
{
    public void printCountyDetails(String countyName, String countyCases,String countyDeaths)
    {
        System.out.println("County Name: "+countyName);
        System.out.println("County Cases: " + countyCases);
        System.out.println("CountyDeaths: " + countyDeaths);
    }
}