package com.accenture.training_task.flightData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@ApiModel(description = "Details about Flight Data")
public class FlightData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@NotEmpty(message = "Departure airport cannot be empty.")
	@Column(name = "departure_airport")
	@ApiModelProperty(notes = "Departure airport of the flight. Field cannot be empty.")
	private String departureAirport;
	
	@NotEmpty(message = "Arrival airport cannot be empty.")
	@Column(name = "arrival_airport")
	@ApiModelProperty(notes = "Arrival airport of the flight. Field cannot be empty.")
	private String arrivalAirport;
	
	@NotEmpty(message = "Airline cannot be empty.")
	@ApiModelProperty(notes = "Airline of the flight. Field cannot be empty.")
	private String airline;
	
	@NotEmpty(message = "Flight number cannot be empty.")
	@Column(name = "flight_number")
	@ApiModelProperty(notes = "Flight number of the flight. Field cannot be empty.")
	private String flightNumber;
	
	protected FlightData() {
		
	}
	
	public FlightData(
			@NotEmpty String departureAirport,
			@NotEmpty String arrivalAirport,
			@NotEmpty String airline,
			@NotEmpty String flightNumber) {
		this.departureAirport = departureAirport;
		this.arrivalAirport = arrivalAirport;
		this.airline = airline;
		this.flightNumber = flightNumber;
	}
	
	public Integer getId() {
		return id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepartureAirport() {
		return departureAirport;
	}
	public void setDepartureAirport(String departureAirport) {
		this.departureAirport = departureAirport;
	}
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		this.arrivalAirport = arrivalAirport;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}

	@Override
	public String toString() {
		return "FlightData [departureAirport=" + departureAirport + ", arrivalAirport=" + arrivalAirport
				+ ", airline=" + airline + ", flightNumber=" + flightNumber + "]";
	}
}
