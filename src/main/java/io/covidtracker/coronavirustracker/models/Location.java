package io.covidtracker.coronavirustracker.models;

public class Location {

    private String state;
    private String county;
    private int cases;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getCases() { return cases; }

    public void setCases(int cases) { this.cases = cases; }

    @Override
    public String toString() {
        return "Location{" +
                "state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", cases=" + cases +
                '}';
    }
}
