package CSC4410.CovidTracker.model;

/**
 * An immutable record representing global coordinate data for a given County.
 */
public class CountyLocation {
    private final int fipsCode;
    private final double latitude;
    private final double longitude;

    @Override
    public String toString() {
        return "CountyLocation{" +
                "fipsCode=" + fipsCode +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
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
     * @return The county's global latitude coordinate.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return The county's global longitude coordinate.
     */
    public double getLongitude() {
        return longitude;
    }

    public CountyLocation(int fipsCode,  double latitude, double longitude) {
        this.fipsCode = fipsCode;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
