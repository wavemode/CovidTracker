package CSC4410.CovidTracker.operation.query;

import CSC4410.CovidTracker.model.County;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CountySearchQuery extends Query {
    private String search;
    private ArrayList<County> results = null;

    public void setSearch(String search) {
        this.search = search;
    }

    public Iterable<County> getResults() {
        return results;
    }

    public CountySearchQuery(String search) {
        this.search = search;
    }

    @Override
    public void execute() throws SQLException {
        var stmt = getStatement();
        stmt.setString(1, "%" + search.toLowerCase() + "%");
        stmt.setString(2, "%" + search.toLowerCase() + "%");

        ResultSet rs = stmt.executeQuery();
        this.results = new ArrayList<>();
        while (rs.next()) {
            results.add(County.fromResultSet(rs));
        }
    }

    @Override
    protected String getStatementBody() {
        return "SELECT *, " +
                "   LOWER(county_name) AS lower_name, " +
                "   LOWER(county_state_code) AS lower_state_code " +
                "FROM county_data " +
                "HAVING lower_name LIKE ? " +
                "OR lower_state_code LIKE ? " +
                "LIMIT 10 ";
    }
}
