package com.example.CovidTracker;

import com.example.CovidTracker.model.Location;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
@EnableScheduling
public class ExampleSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExampleSpringBootApplication.class, args);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("location");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Location location = entityManager.find(Location.class,2);
		System.out.println(location);
	}

}
