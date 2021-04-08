package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.County;
import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.operation.query.CountyNameInsertQuery;
import CSC4410.CovidTracker.operation.query.LocationUpdateQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationUpdateQueryTest {
    @BeforeAll
    public static void setupTests() throws SQLException {
        // first we insert a test column
        var countyName = new CountyName(8888, "test", "TT");
        var query = new CountyNameInsertQuery(countyName);
        query.execute();
    }

    @Test
    public void executeTest() throws SQLException {
        // first, execute the update query
        var location = new CountyLocation(8888, 7777, 6666);
        var updateQuery = new LocationUpdateQuery(location);
        updateQuery.execute();

        // ensure that the county has updated
        assertEquals(County.byCode(8888).getLatitude(), 7777);
        assertEquals(County.byCode(8888).getLongitude(), 6666);
    }

    @Test
    public void setCountyLocationTest() throws SQLException {
        // first create a location query without setting a location
        var updateQuery = new LocationUpdateQuery(null);

        // set the location
        var location = new CountyLocation(8888, 4444, 3333);
        updateQuery.setCountyLocation(location);

        // execute the query
        updateQuery.execute();

        // ensure that the county has updated
        assertEquals(County.byCode(8888).getLatitude(), 4444);
        assertEquals(County.byCode(8888).getLongitude(), 3333);
    }
}
