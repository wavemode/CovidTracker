package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.operation.request.CountyNamesRequest;
import CSC4410.CovidTracker.operation.request.CovidDataRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class CovidDataRequestTest {

    @Test
    public void getResultsTest() throws SQLException, IOException, ParseException, InterruptedException {
        CovidDataRequest request = new CovidDataRequest();
        assertEquals(request.getResults(),null);
        request.execute();
        assertNotEquals(request.getResults(),null);
    }

    @Test
    public void executeTest() throws SQLException, IOException, ParseException, InterruptedException {

        var request = new CovidDataRequest();
        request.execute();

        assertNotNull(request.getResults());

        for (CountyCovidData result : request.getResults()) {
            assertNotNull(result.getCases());
            assertNotNull(result.getDailyCases());
            assertNotNull(result.getDeaths());
            assertNotNull(result.getFipsCode());
            break; // we don't need to test every single row
        }
    }
}
