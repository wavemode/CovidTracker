package CSC4410.CovidTracker.state;

import CSC4410.CovidTracker.counties.CountyData;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "states")
public class StateData {
    @Id
    @Column()
    private String state;

    @OneToMany(mappedBy = "stateData")

    private List<CountyData> countyData = new ArrayList<CountyData>();

    public StateData(String state) {
        this.state = state;
    }

    public StateData() {
    }



    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<CountyData> getCountyData() {
        return countyData;
    }

    public void setCountyData(List<CountyData> countyData) {
        this.countyData = countyData;
    }
}
