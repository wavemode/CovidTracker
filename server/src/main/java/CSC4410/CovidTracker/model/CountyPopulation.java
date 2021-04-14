package CSC4410.CovidTracker.model;

/**
 * An immutable record representing population data for a given County.
 */
public class CountyPopulation {
    private final int fipsCode;
    private final int population;

    @Override
    public String toString() {
        return "CountyPopulation{" +
                "fipsCode=" + fipsCode +
                ", population=" + population +
                '}';
    }

    /**
     * @return The unique FIPS (Federal Information Processing Standard) code
     * of this county.
     */
    public int getFipsCode() {
        return fipsCode;
    }

    /**
     * @return The county's current estimated population.
     */
    public int getPopulation() {
        return population;
    }

    public CountyPopulation(int fipsCode, int population) {
        this.fipsCode = fipsCode;
        this.population = population;
    }

}
