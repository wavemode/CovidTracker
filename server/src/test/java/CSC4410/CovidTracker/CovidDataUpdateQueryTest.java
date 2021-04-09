package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.County;
import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.operation.query.CountyNameInsertQuery;
import CSC4410.CovidTracker.operation.query.CovidDataUpdateQuery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CovidDataUpdateQueryTest {
    @BeforeAll
    public static void setupTests() throws SQLException {
        // first we insert a test column
        var countyName = new CountyName(9999, "test", "TT");
        var query = new CountyNameInsertQuery(countyName);
        query.execute();
    }

    @Test
    public void executeTest() throws SQLException {
        // first, execute the update query
        var covidData = new CountyCovidData(9999, 100, 200, 300);
        var updateQuery = new CovidDataUpdateQuery(covidData);
        updateQuery.execute();

        // ensure that the county has updated
        assertEquals(County.byCode(9999).getCases(), 100);
        assertEquals(County.byCode(9999).getDailyCases(), 200);
        assertEquals(County.byCode(9999).getDeaths(), 300);
    }

    @Test
    public void setCountyTest() throws SQLException {
        // create an update query without setting a county
        var updateQuery = new CovidDataUpdateQuery(null);

        // set the county
        updateQuery.setCounty(new CountyCovidData(9999, 500, 600, 700));
        updateQuery.execute();

        // ensure that the county has updated
        var county = County.byCode(9999);
        assertEquals(county.getCases(), 500);
        assertEquals(county.getDailyCases(), 600);
        assertEquals(county.getDeaths(), 700);
    }
}
