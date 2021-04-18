package CSC4410.CovidTracker.operation.query;

import CSC4410.CovidTracker.model.CountyLocation;

import java.sql.SQLException;

public class LocationUpdateQuery extends Query {
    private CountyLocation countyLocation;

    public void setCountyLocation(CountyLocation countyLocation) {
        this.countyLocation = countyLocation;
    }

    public LocationUpdateQuery(CountyLocation countyLocation) {
        this.countyLocation = countyLocation;
    }

    @Override
    protected String getStatementBody() {
        return "UPDATE county_data " +
                "SET county_latitude = ?, county_longitude = ? " +
                "WHERE county_fips = ?";
    }

    @Override
    public void execute() throws SQLException {
        var stmt = getStatement();
        stmt.setDouble(1, countyLocation.getLatitude());
        stmt.setDouble(2, countyLocation.getLongitude());
        stmt.setInt(3, countyLocation.getFipsCode());

        stmt.execute();
    }

}
