package CSC4410.CovidTracker.model;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An immutable database record representing covid and related
 * data for a given County.
 */
public class County {

    public static County fromResultSet(ResultSet rs) throws SQLException {
        int fipsCode = rs.getInt("county_fips_code");
        String name = rs.getString("county_name");
        String stateCode = rs.getString("county_state_code");
        int cases = rs.getInt("county_cases");
        int dailyCases = rs.getInt("county_daily_cases");
        int deaths = rs.getInt("county_deaths");
        int population = rs.getInt("county_population");
        double latitude = rs.getDouble("county_latitude");
        double longitude = rs.getDouble("county_longitude");

        return new County(fipsCode, name, stateCode, cases, dailyCases, deaths,
                            population, latitude, longitude);
    }

    private final CountyCovidData covidData;
    private final CountyLocation countyLocation;
    private final CountyName countyName;
    private final CountyPopulation countyPopulation;

    @Override
    public String toString() {
        return "County{" +
                "fips=" + getFipsCode() +
                ", name='" + getName() + '\'' +
                ", stateCode='" + getStateCode() + '\'' +
                ", cases=" + getCases() +
                ", dailyCases=" + getDailyCases() +
                ", deaths=" + getDeaths() +
                ", population=" + getPopulation() +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                '}';
    }

    /**
     * @return The unique FIPS (Federal Information Processing Standard) code
     * of this county.
     */
    public int getFipsCode() {
        return countyName.getFipsCode();
    }

    /**
     * @return The name of the County, including any designation
     * ("County", "Parish", etc.)
     *
     * e.g. instead of returning "Cook County, Illinois", this method should
     * return "Cook County".
     */
    public String getName() {
        return countyName.getName();
    }

    /**
     * @return The two-letter state code this county is within.
     */
    public String getStateCode() {
        return countyName.getStateCode();
    }

    /**
     * @return The total number of positive Covid-19 cases in this county since
     * the start of the pandemic.
     */
    public int getCases() {
        return covidData.getCases();
    }

    /**
     * @return The number of positive Covid-19 cases in this county in the last
     * 24 hours.
     */
    public int getDailyCases() {
        return covidData.getDailyCases();
    }

    /**
     * @return The total number of deaths from Covid-19 that have occurred in
     * this county since the start of the pandemic.
     */
    public int getDeaths() {
        return covidData.getDeaths();
    }

    /**
     * @return The county's current estimated population. Useful for determining
     * whether this location is a
     * Covid-19 hotspot.
     */
    public int getPopulation() {
        return countyPopulation.getPopulation();
    }

    /**
     * @return The county's global latitude coordinate.
     */
    public double getLatitude() {
        return countyLocation.getLatitude();
    }

    /**
     * @return The county's global longitude coordinate.
     */
    public double getLongitude() {
        return countyLocation.getLongitude();
    }

    public County(int fipsCode, String name, String stateCode, int cases,
                  int dailyCases, int deaths, int population,
                  double latitude, double longitude) {
        this.covidData = new CountyCovidData(fipsCode, cases, dailyCases, deaths);
        this.countyLocation = new CountyLocation(fipsCode, latitude, longitude);
        this.countyName = new CountyName(fipsCode, name, stateCode);
        this.countyPopulation = new CountyPopulation(fipsCode, population);
    }

    public County(CountyCovidData covidData, CountyLocation countyLocation, CountyName countyName, CountyPopulation countyPopulation) {
        this.covidData = covidData;
        this.countyLocation = countyLocation;
        this.countyName = countyName;
        this.countyPopulation = countyPopulation;
    }
}
