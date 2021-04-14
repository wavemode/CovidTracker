package CSC4410.CovidTracker;

import CSC4410.CovidTracker.model.County;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.operation.query.CountyCodeQuery;
import CSC4410.CovidTracker.operation.query.CountyLocationQuery;
import CSC4410.CovidTracker.operation.request.CountyNamesRequest;
import CSC4410.CovidTracker.service.DataUpdateService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CountyLocationQueryTest {
    @BeforeAll
    public static void setupTests() throws SQLException, IOException {
        DataUpdateService.createDataTable();
        DataUpdateService.insertCountyNames();
        DataUpdateService.updateCountyLocations();

    }

    @Test
    public void setCoordinateTest() throws SQLException, IOException {
        var countyLocationQuery=new CountyLocationQuery(32.5349201866,-86.642749235);
        countyLocationQuery.setCoordinate(30.6609696648,-87.7498400784);
        countyLocationQuery.execute();
        assertEquals(countyLocationQuery.getResult().getName(),"Baldwin County");
    }

    @Test
    public void getResultsTest() throws SQLException, IOException {
        var countyLocationQuery=new CountyLocationQuery(25.243213,21.242321);
        assertEquals(countyLocationQuery.getResult(),null);
        countyLocationQuery.execute();
        assertNotEquals(countyLocationQuery.getResult(),null);
    }
    @Test
    public void executeTest() throws SQLException, IOException {
        var countyLocationQuery=new CountyLocationQuery(32.5349201866,-86.642749235);
        countyLocationQuery.execute();
        assertEquals(countyLocationQuery.getResult().getName(),"Autauga County");
    }
}
