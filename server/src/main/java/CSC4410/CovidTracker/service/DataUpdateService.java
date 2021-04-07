package CSC4410.CovidTracker.service;

import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.model.CountyPopulation;
import CSC4410.CovidTracker.operation.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class DataUpdateService {

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24) // run once per day
    public void fetchVirusData() throws IOException, SQLException {

        // create county data table
        new CountyTableCreateQuery().execute();

        // request and insert county names
        var getNames = new CountyNamesRequest();
        getNames.execute();

        var insertName = new CountyNameInsertQuery(null);
        insertName.begin();
        for (CountyName record : getNames.getResults()) {
            insertName.setCounty(record);
            insertName.execute();
        }
        insertName.commit();

        // request and insert county populations
        var getPop = new PopulationDataRequest();
        getPop.execute();

        var updatePop = new PopulationUpdateQuery(null);
        updatePop.begin();
        for (CountyPopulation population : getPop.getResults()) {
            updatePop.setCountyPopulation(population);
            updatePop.execute();
        }
        updatePop.commit();

        // request and insert county locations
        var getLoc = new LocationDataRequest();
        getLoc.execute();

        var updateLoc = new LocationUpdateQuery(null);
        updatePop.begin();
        for (CountyLocation location : getLoc.getResults()) {
            updateLoc.setCountyLocation(location);
            updateLoc.execute();
        }
        updateLoc.commit();

        // request and update covid data
        var query = new CovidDataRequest();
        try {
            query.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Iterable<CountyCovidData> results = query.getResults();
        var insert = new CovidDataUpdateQuery(null);
        insert.begin();

        for (var result : results) {
            insert.setCounty(result);
            insert.execute();
        }

        insert.commit();

        System.out.println("Updated data.");

    }

}
