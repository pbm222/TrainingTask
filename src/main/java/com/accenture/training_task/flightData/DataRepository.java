package com.accenture.training_task.flightData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<FlightData, Integer> {
	
	FlightData findByFlightNumber(String flightNumber);

}
