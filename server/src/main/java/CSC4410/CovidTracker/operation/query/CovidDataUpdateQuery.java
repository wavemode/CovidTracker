package CSC4410.CovidTracker.operation.query;

import CSC4410.CovidTracker.model.CountyCovidData;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Query which updates the Covid-19 data for a given county (cases, dailyCases,
 * and deaths).
 */
public class CovidDataUpdateQuery extends Query {

    private CountyCovidData county;

    public void setCounty(CountyCovidData county) {
        this.county = county;
    }

    public CovidDataUpdateQuery(CountyCovidData county) {
        this.county = county;
    }

    @Override
    protected String getStatementBody() {
        return "UPDATE county_data " +
                "SET county_cases = ?, " +
                "county_daily_cases = ?, " +
                "county_deaths = ? " +
                "WHERE county_fips = ?";
    }

    @Override
    public void execute() throws SQLException {

        PreparedStatement stmt = getStatement();

        stmt.setInt(1, county.getCases());
        stmt.setInt(2, county.getDailyCases());
        stmt.setInt(3, county.getDeaths());
        stmt.setInt(4, county.getFipsCode());

        stmt.execute();
    }
}
