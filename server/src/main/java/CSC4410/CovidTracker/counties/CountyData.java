package CSC4410.CovidTracker.counties;

import CSC4410.CovidTracker.state.StateData;

import javax.persistence.*;

@Entity

public class CountyData {
    @Id
    private String county;
    private int cases;
    private int deaths;

    @ManyToOne
    private StateData stateData;

    public StateData getStateData() {
        return stateData;
    }

    public void setStateData(StateData stateData) {
        this.stateData = stateData;
    }

    public CountyData(String county, int cases, int deaths) {
        this.county = county;
        this.cases = cases;
        this.deaths = deaths;
    }

    public CountyData() {
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    @Override
    public String toString() {
        return "County{" +
                "county='" + county + '\'' +
                ", cases=" + cases +
                ", deaths='" + deaths + '\'' +
                '}';
    }

}
