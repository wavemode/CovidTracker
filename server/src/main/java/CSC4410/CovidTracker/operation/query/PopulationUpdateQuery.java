package CSC4410.CovidTracker.operation.query;

import CSC4410.CovidTracker.model.CountyPopulation;

import java.sql.SQLException;

public class PopulationUpdateQuery extends Query {
    private CountyPopulation countyPopulation;

    public void setCountyPopulation(CountyPopulation countyPopulation) {
        this.countyPopulation = countyPopulation;
    }

    public PopulationUpdateQuery(CountyPopulation countyPopulation) {
        this.countyPopulation = countyPopulation;
    }

    @Override
    protected String getStatementBody() {
        return "UPDATE county_data " +
                "SET county_population = ? " +
                "WHERE county_fips = ?";
    }

    @Override
    public void execute() throws SQLException {
        var stmt = getStatement();
        stmt.setInt(1, countyPopulation.getPopulation());
        stmt.setInt(2, countyPopulation.getFipsCode());

        stmt.execute();
    }

}
