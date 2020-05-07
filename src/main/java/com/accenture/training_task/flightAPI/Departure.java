
package com.accenture.training_task.flightAPI;

import java.util.HashMap;
import java.util.Map;

public class Departure {

    private String airport;
    private String timezone;
    private String scheduled;
    private String estimated;

    public Departure() {
    }

    public Departure(String airport, String timezone, String scheduled, String estimated) {
        this.airport = airport;
        this.timezone = timezone;
        this.scheduled = scheduled;
        this.estimated = estimated;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }


    public String getScheduled() {
        return scheduled;
    }

    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }

    public String getEstimated() {
        return estimated;
    }

    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }

    @Override
    public String toString() {
        return "Departure{" +
                "airport='" + airport + '\'' +
                ", timezone='" + timezone + '\'' +
                ", scheduled='" + scheduled + '\'' +
                ", estimated='" + estimated + '\'' +
                '}';
    }
}
