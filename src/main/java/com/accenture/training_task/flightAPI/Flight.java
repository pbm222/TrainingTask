
package com.accenture.training_task.flightAPI;

import java.util.HashMap;
import java.util.Map;

public class Flight {

    private String number;

    public Flight(String number) {
        this.number = number;
    }

    public Flight() {
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + number + '\'' +
                '}';
    }
}
