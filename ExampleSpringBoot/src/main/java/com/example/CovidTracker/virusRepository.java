package com.example.CovidTracker;

import com.example.CovidTracker.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface virusRepository extends JpaRepository<Location,String> {
}
