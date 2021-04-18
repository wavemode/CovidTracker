package CSC4410.CovidTracker.operation.query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A database query which creates the table for data by county, if it
 * does not already exist.
 */
public class CountyTableCreateQuery extends Query {
    @Override
    protected String getStatementBody() {
        return "CREATE TABLE IF NOT EXISTS county_data (" +
                "county_fips INT PRIMARY KEY," +
                "county_name VARCHAR(256)," +
                "county_state_code CHAR(2)," +
                "county_cases INT," +
                "county_daily_cases INT," +
                "county_deaths INT," +
                "county_population INT," +
                "county_latitude DOUBLE," +
                "county_longitude DOUBLE)";
    }

    public void execute() throws SQLException {
        PreparedStatement stmt = getStatement();
        stmt.execute();

    }
}
