package com.accenture.training_task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class FlightData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotEmpty(message = "Departure airport cannot be empty.")
	private String departureairport;
	
	@NotEmpty(message = "Arrival airport cannot be empty.")
	private String arrivalairport;
	
	@NotEmpty(message = "Airline cannot be empty.")
	private String airline;
	
	@NotEmpty(message = "Flight number cannot be empty.")
	private String flightnumber;
	
	protected FlightData() {
		
	}
	
	public FlightData(
			@NotEmpty String departureAirport,
			@NotEmpty String arrivalAirport,
			@NotEmpty String airline,
			@NotEmpty String flightNumber) {
		this.departureairport = departureAirport;
		this.arrivalairport = arrivalAirport;
		this.airline = airline;
		this.flightnumber = flightNumber;
	}
	
	public String getFlightNumber() {
		return flightnumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightnumber = flightNumber;
	}
	public String getDepartureAirport() {
		return departureairport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureairport = departureAirport;
	}
	public String getArrivalAirport() {
		return arrivalairport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalairport = arrivalAirport;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}

	@Override
	public String toString() {
		return "FlightData [departureAirport=" + departureairport + ", arrivalAirport=" + arrivalairport
				+ ", airline=" + airline + ", flightNumber=" + flightnumber + "]";
	}
}
