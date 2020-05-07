
package com.accenture.training_task.flightAPI;


import java.util.HashMap;
import java.util.Map;

public class Airline {

    private String name;

    public Airline(String name) {
        this.name = name;
    }

    public Airline() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + name + '\'' +
                '}';
    }
}
