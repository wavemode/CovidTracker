package CSC4410.CovidTracker.model;

/**
 * An immutable record representing the full name and state code of a given county.
 */
public class CountyName {
    private final String stateCode;
    private final String name;
    private final int fipsCode;


    public CountyName(int fipsCode, String name, String stateCode) {
        this.stateCode = stateCode;
        this.name = name;
        this.fipsCode = fipsCode;
    }

    /**
     * @return The unique FIPS (Federal Information Processing Standard) code
     * of this county.
     */
    public int getFipsCode() {
        return fipsCode;
    }

    /**
     * @return The two-letter state code this county is within.
     */
    public String getStateCode() {
        return stateCode;
    }

    /**
     * @return The full name of the County, including any designation ("County", "Parish", etc.)
     */
    public String getName() {
        return name;
    }
}
