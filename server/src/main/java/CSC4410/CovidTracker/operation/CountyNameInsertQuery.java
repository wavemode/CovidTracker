package CSC4410.CovidTracker.operation;

import CSC4410.CovidTracker.model.CountyCovidData;
import CSC4410.CovidTracker.model.CountyName;
import CSC4410.CovidTracker.util.Query;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountyNameInsertQuery extends Query {

    private CountyName county;

    public void setCounty(CountyName county) {
        this.county = county;
    }

    public CountyNameInsertQuery(CountyName county) {
        this.county = county;
    }

    @Override
    protected String getStatementBody() {
        return "REPLACE INTO county_data (county_fips, county_name, county_state_code) " +
                "VALUES (?, ?, ?)";
    }

    @Override
    public void execute() throws SQLException {

        PreparedStatement stmt = getStatement();

        stmt.setInt(1, county.getFipsCode());
        stmt.setString(2, county.getName());
        stmt.setString(3, county.getStateCode());

        stmt.execute();
    }
}
