package CSC4410.CovidTracker.operation;

import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.util.Csv;
import CSC4410.CovidTracker.util.Filesystem;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;

public class CountyNamesRequest {
    private static final String DATA_FILE = "data/us_county_coordinates.csv";

    private ArrayList<CountyName> names;

    public Iterable<CountyName> getResults() {
        return names;
    }

    public CountyNamesRequest() {}

    public void execute() throws IOException {

        String data = Filesystem.readFile(DATA_FILE);
        Iterable<CSVRecord> parsed = Csv.parseCsv(data);

        names = new ArrayList<CountyName>();

        for (CSVRecord record : parsed) {

            int fips;
            String name, stateCode;

            try {
                fips = Integer.parseInt(record.get("GEOID"));
                name = record.get("NAMELSAD");
                stateCode = record.get("STUSAB");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            names.add(new CountyName(fips, name, stateCode));

        }

    }
}
