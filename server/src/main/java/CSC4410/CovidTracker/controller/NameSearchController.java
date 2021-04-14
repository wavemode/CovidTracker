package CSC4410.CovidTracker.controller;

import CSC4410.CovidTracker.model.County;
import CSC4410.CovidTracker.operation.query.CountyLocationQuery;
import CSC4410.CovidTracker.operation.query.CountySearchQuery;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class NameSearchController {

    private final CountySearchQuery query = new CountySearchQuery(null);

    @RequestMapping(value = "/search")
    @ResponseBody
    public ArrayList<County> search(@RequestParam(name="q", required=true) String search) throws SQLException {

        // search for a county matching the given search parameter
        query.setSearch(search);
        query.execute();
        var results = query.getResults();
        var list = new ArrayList<County>();
        for (var result : results)
            list.add(result);

        return list;

    }
}