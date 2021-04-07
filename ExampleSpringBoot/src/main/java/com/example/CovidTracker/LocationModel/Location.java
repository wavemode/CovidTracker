package com.example.CovidTracker.LocationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LocationData")
public class Location {

    @Id
    @Column(nullable = false)
    private String county;
    private String state;
    private int cases;
    private String deaths;

    public Location(String county, String state, int cases, String deaths) {
        this.county = county;
        this.state = state;
        this.cases = cases;
        this.deaths = deaths;
    }

    public Location() {
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

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

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    @Override
    public String toString() {
        return "Location{" +
                "state='" + state + '\'' +
                ", county='" + county + '\'' +
                ", cases=" + cases +
                '}';
    }
}