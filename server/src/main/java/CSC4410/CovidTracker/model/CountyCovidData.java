package CSC4410.CovidTracker.model;

/**
 * An immutable record representing covid data for a given County.
 */
public class CountyCovidData {
    private final int fipsCode;
    private final int cases;
    private final int dailyCases;
    private final int deaths;

    /**
     * @return The unique FIPS (Federal Information Processing Standard) code
     * of this county.
     */
    public int getFipsCode() {
        return fipsCode;
    }

    /**
     * @return The total number of positive Covid-19 cases in this county since the start of the pandemic.
     */
    public int getCases() {
        return cases;
    }

    /**cou
     * @return The number of positive Covid-19 cases in this county in the last 24 hours.
     */
    public int getDailyCases() {
        return dailyCases;
    }

    /**
     * @return The total number of deaths from Covid-19 that have occurred in this county since the start of the pandemic.
     */
    public int getDeaths() {
        return deaths;
    }

    @Override
    public String toString() {
        return "CountyCovidData{" +
                "fipsCode=" + fipsCode +
                ", cases=" + cases +
                ", dailyCases=" + dailyCases +
                ", deaths=" + deaths +
                '}';
    }

    public CountyCovidData(int fips, int cases, int dailyCases, int deaths) {
        this.fipsCode = fips;
        this.cases = cases;
        this.dailyCases = dailyCases;
        this.deaths = deaths;
    }
}

