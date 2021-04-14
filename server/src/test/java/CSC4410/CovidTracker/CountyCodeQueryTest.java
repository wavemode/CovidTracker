package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.operation.query.CountyCodeQuery;
import CSC4410.CovidTracker.operation.request.CountyNamesRequest;
import CSC4410.CovidTracker.service.DataUpdateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CountyCodeQueryTest {
    @BeforeAll
    public static void setupTests() throws SQLException, IOException {
        DataUpdateService.createDataTable();
        DataUpdateService.insertCountyNames();

    }

    @Test
    public void setFipsCodeTest() throws SQLException, IOException {
        var countyCodeQuery=new CountyCodeQuery(1001);
        countyCodeQuery.setFipsCode(1003);
        countyCodeQuery.execute();
        assertEquals(countyCodeQuery.getResult().getFipsCode(),1003);
    }

    @Test
    public void getResultsTest() throws SQLException, IOException {
        var countyCodeQuery=new CountyCodeQuery(1001);
        assertEquals(countyCodeQuery.getResult(),null);
        countyCodeQuery.execute();
        assertNotEquals(countyCodeQuery.getResult(),null);
    }
    @Test
    public void executeTest() throws SQLException, IOException {
        var countyCodeQuery=new CountyCodeQuery(1001);
        countyCodeQuery.execute();
        assertEquals(countyCodeQuery.getResult().getFipsCode(),1001);

    }
}
