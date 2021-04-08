package CSC4410.CovidTracker.operation.query;

import CSC4410.CovidTracker.model.County;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Query which fetches the county that is closest to a given geographic
 * coordinate.
 */
public class CountyLocationQuery extends Query {

    private double latitude, longitude;
    private County result = null;

    public void setCoordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return The resulting County, or null if the query has not been run yet.
     */
    public County getResult() {
        return result;
    }

    public CountyLocationQuery(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    protected String getStatementBody() {
        return "SELECT *, ABS(county_latitude - ?) + " +
                    "ABS(county_longitude - ?) " +
                    "AS distance " +
                "FROM county_data " +
                "WHERE county_latitude IS NOT NULL " +
                "AND county_longitude IS NOT NULL " +
                "ORDER BY distance ASC " +
                "LIMIT 1";
    }


    @Override
    public void execute() throws SQLException {

        PreparedStatement stmt = getStatement();
        stmt.setDouble(1, latitude);
        stmt.setDouble(2, longitude);

        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            this.result = County.fromResultSet(result);
        }
    }
}
