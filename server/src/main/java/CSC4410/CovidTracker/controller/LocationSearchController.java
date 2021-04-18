package CSC4410.CovidTracker.controller;

import CSC4410.CovidTracker.model.County;
import CSC4410.CovidTracker.operation.query.CountyLocationQuery;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class LocationSearchController {

    private final CountyLocationQuery query = new CountyLocationQuery(0, 0);

    @RequestMapping(value = "/location")
    @ResponseBody
    public County search(@RequestParam(name="lat", required=true) double latitude,
                         @RequestParam(name="long", required=true) double longitude) throws SQLException {

        /* search for the given values of lat and long and return
           the nearest county */
        query.setCoordinate(latitude, longitude);
        query.execute();
        return query.getResult();

    }
}