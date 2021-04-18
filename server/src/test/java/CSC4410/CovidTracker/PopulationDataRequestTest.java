package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.model.CountyPopulation;
import CSC4410.CovidTracker.operation.request.CountyNamesRequest;
import CSC4410.CovidTracker.operation.request.CovidDataRequest;
import CSC4410.CovidTracker.operation.request.LocationDataRequest;
import CSC4410.CovidTracker.operation.request.PopulationDataRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationDataRequestTest {

    @Test
    public void getResultsTest() throws SQLException, IOException, ParseException, InterruptedException {
        PopulationDataRequest request = new PopulationDataRequest();
        assertEquals(request.getResults(),null);
        request.execute();
        assertNotEquals(request.getResults(),null);
    }

    @Test
    public void executeTest() throws SQLException, IOException, ParseException, InterruptedException {

        var request = new PopulationDataRequest();
        request.execute();

        assertNotNull(request.getResults());

        for (CountyPopulation result : request.getResults()) {
            assertNotNull(result.getPopulation());
            assertNotNull(result.getFipsCode());
            break; // we don't need to test every single row
        }
    }
}
