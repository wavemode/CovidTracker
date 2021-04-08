package CSC4410.CovidTracker.operation.request;

import CSC4410.CovidTracker.model.CountyPopulation;
import CSC4410.CovidTracker.util.Csv;
import CSC4410.CovidTracker.util.Filesystem;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.ArrayList;

public class PopulationDataRequest {
    private static final String DATA_FILE = "data/us_county_population.csv";

    private ArrayList<CountyPopulation> populations;

    public Iterable<CountyPopulation> getResults() {
        return populations;
    }

    public PopulationDataRequest() {}

    public void execute() throws IOException {

        String data = Filesystem.readFile(DATA_FILE);
        Iterable<CSVRecord> parsed = Csv.parseCsv(data);

        populations = new ArrayList<>();

        for (CSVRecord record : parsed) {

            int fips, population;

            try {
                fips = Integer.parseInt(record.get("FIPStxt"));
                population = Integer.parseInt(record.get("POP_ESTIMATE_2019"));
            } catch (Exception e) {
                // ignore parse failures
                continue;
            }

            populations.add(new CountyPopulation(fips, population));


        }

    }
}
