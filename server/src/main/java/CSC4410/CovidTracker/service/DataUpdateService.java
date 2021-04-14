package CSC4410.CovidTracker.service;

import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyLocation;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.model.CountyPopulation;
import CSC4410.CovidTracker.operation.query.*;
import CSC4410.CovidTracker.operation.request.CountyNamesRequest;
import CSC4410.CovidTracker.operation.request.CovidDataRequest;
import CSC4410.CovidTracker.operation.request.LocationDataRequest;
import CSC4410.CovidTracker.operation.request.PopulationDataRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class DataUpdateService {

    /**
     * Updates all of the county data in the database.
     * @throws IOException
     * @throws SQLException
     */
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24) // run once per day
    public void fetchVirusData() throws IOException, SQLException {

        insertCountyNames();

        updateCountyPopulations();

        updateCountyLocations();

        updateCovidData();

        System.out.println("Updated data.");

    }

    // request and update covid data
    private void updateCovidData() throws SQLException {
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
    }

    // request and insert county locations
    private void updateCountyLocations() throws IOException, SQLException {
        var getLoc = new LocationDataRequest();
        getLoc.execute();

        var updateLoc = new LocationUpdateQuery(null);
        updateLoc.begin();
        for (CountyLocation location : getLoc.getResults()) {
            updateLoc.setCountyLocation(location);
            updateLoc.execute();
        }
        updateLoc.commit();
    }

    // request and insert county populations
    private void updateCountyPopulations() throws IOException, SQLException {
        var getPop = new PopulationDataRequest();
        getPop.execute();

        var updatePop = new PopulationUpdateQuery(null);
        updatePop.begin();
        for (CountyPopulation population : getPop.getResults()) {
            updatePop.setCountyPopulation(population);
            updatePop.execute();
        }
        updatePop.commit();
    }

    // request and insert county names
    private void insertCountyNames() throws IOException, SQLException {
        var getNames = new CountyNamesRequest();
        getNames.execute();

        var insertName = new CountyNameInsertQuery(null);
        insertName.begin();
        for (CountyName record : getNames.getResults()) {
            insertName.setCounty(record);
            insertName.execute();
        }
        insertName.commit();
    }

}
