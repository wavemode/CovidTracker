package CSC4410.CovidTracker.counties;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static util.Http.getClient;

public class CountyDataRequestor {

    private static final String COUNTY_DATA_URI = "https://raw.githubusercontent.com/nytimes/covid-19-data/master/live/us-counties.csv";

    public static HttpResponse<String> getCountyData() throws IOException, InterruptedException {
        HttpClient client = getClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(COUNTY_DATA_URI))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());

    }
}
