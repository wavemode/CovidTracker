package CSC4410.CovidTracker.operation;

import CSC4410.CovidTracker.model.County;
import CSC4410.CovidTracker.util.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Query which fetches the county with the given FIPS code.
 */
public class CountyCodeQuery extends Query {

    private int fipsCode;

    private County result = null;

    public void setFipsCode(int fipsCode) {
        this.fipsCode = fipsCode;
    }


    /**
     * @return The resulting County, or null if the query has not been run yet.
     */
    public County getResult() {
        return result;
    }

    public CountyCodeQuery(int fipsCode) {
        this.fipsCode = fipsCode;
    }

    @Override
    protected String getStatementBody() {
        return "SELECT * " +
                "FROM county_data " +
                "WHERE county_fips = ?";
    }


    @Override
    public void execute() throws SQLException {

        PreparedStatement stmt = getStatement();
        stmt.setInt(1, fipsCode);

        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            this.result = County.fromResultSet(result);
        }
    }
}
