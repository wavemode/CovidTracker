package com.example.CovidTracker.LocationService;

import com.example.CovidTracker.LocationModel.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface virusRepository extends JpaRepository<Location,String> {
    Location findByCounty(String county);

}
