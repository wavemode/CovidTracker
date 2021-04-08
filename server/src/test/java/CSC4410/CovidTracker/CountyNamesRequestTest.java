package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.operation.request.CountyNamesRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CountyNamesRequestTest {

    @Test
    public void getResultsTest() throws SQLException, IOException {
        // getResults should return false when the request has not been executed.
        var request = new CountyNamesRequest();
        assertNull(request.getResults());

        // getResults should not be null after the request has been executed.
        request.execute();
        assertNotNull(request.getResults());
    }

    @Test
    public void executeTest() throws SQLException, IOException {
        // execute should run without error and yield an iterable structure of CountyNames.
        var request = new CountyNamesRequest();
        request.execute();

        assertNotNull(request.getResults());

        for (CountyName result : request.getResults()) {
            assertNotNull(result.getName());
            assertNotNull(result.getStateCode());
            break; // we don't need to test every single row
        }
    }
}
