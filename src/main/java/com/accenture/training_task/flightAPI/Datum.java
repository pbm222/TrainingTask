
package com.accenture.training_task.flightAPI;

public class Datum {

    private String flightDate;
    private String flightStatus;
    private Departure departure;
    private Arrival arrival;
    private Airline airline;
    private Flight flight;

    public Datum() {
    }

    public Datum(String flightDate, String flightStatus, Departure departure, Arrival arrival, Airline airline, Flight flight) {
        this.flightDate = flightDate;
        this.flightStatus = flightStatus;
        this.departure = departure;
        this.arrival = arrival;
        this.airline = airline;
        this.flight = flight;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    public String getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
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

    @Override
    public String toString() {
        return "Datum{" +
                "flightDate='" + flightDate + '\'' +
                ", flightStatus='" + flightStatus + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", airline=" + airline +
                ", flight=" + flight +
                '}';
    }
}
