package com.accenture.training_task;

import com.accenture.training_task.flightAPI.Airline;
import com.accenture.training_task.flightAPI.Arrival;
import com.accenture.training_task.flightAPI.Departure;
import com.accenture.training_task.flightAPI.Flight;

public class FlightData {
	
	Departure departure;
	Arrival arrival;
	Airline airline;
	Flight flight;
	
	public FlightData(Departure departure, Arrival arrival, Airline airline, Flight flight) {
		this.departure = departure;
		this.arrival = arrival;
		this.airline = airline;
		this.flight = flight;
	}
	
	public Departure getDeparture() {
		return departure;
	}
	public void setDeparture(Departure departure) {
		this.departure = departure;
	}
	public Arrival getArrival() {
		return arrival;
	}
	public void setArrival(Arrival arrival) {
		this.arrival = arrival;
	}
	public Airline getAirline() {
		return airline;
	}
	public void setAirline(Airline airline) {
		this.airline = airline;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
