package CSC4410.CovidTracker.operation.request;

import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.util.Csv;
import CSC4410.CovidTracker.util.Filesystem;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;
/**
 * A request for all coordinate location data in the United States.
 * Retrieves the fips code, longitude and latitude of each county.
 */
public class LocationDataRequest {
    private static final String DATA_FILE = "data/us_county_coordinates.csv";

    private ArrayList<CountyLocation> locations;
    public Iterable<CountyLocation> getResults() {
        return locations;
    }

    public LocationDataRequest() {}

    public void execute() throws IOException {

        String data = Filesystem.readFile(DATA_FILE);
        Iterable<CSVRecord> parsed = Csv.parseCsv(data);

        locations = new ArrayList<CountyLocation>();

        for (CSVRecord record : parsed) {

            int fips;
            double latitude, longitude;

            try {
                fips = Integer.parseInt(record.get("GEOID"));
                String[] coordinates = record.get("GEOPOINT").split("; ");
                latitude = Double.parseDouble(coordinates[0]);
                longitude = Double.parseDouble(coordinates[1]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            locations.add(new CountyLocation(fips, latitude, longitude));

        }

    }
}
