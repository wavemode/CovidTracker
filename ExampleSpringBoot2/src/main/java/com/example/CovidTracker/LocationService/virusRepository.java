package com.example.CovidTracker.LocationService;

import com.example.CovidTracker.LocationModel.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface virusRepository extends JpaRepository<Location,String> {
      Optional<Location> findByStateIgnoreCase( String state);

}
