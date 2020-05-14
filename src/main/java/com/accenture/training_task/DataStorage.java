package com.accenture.training_task;

import com.accenture.training_task.model.FlightData;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class DataStorage {

    private HashMap<String, FlightData> data;

    public DataStorage() {
        this.data = new HashMap();
    }

    public HashMap<String, FlightData> getData() {
        return data;
    }

    public FlightData getSpecificData(Integer flightNumber) {
        return this.data.get(flightNumber);
    }

    public void addData(String flightNumber, FlightData data) {
        this.data.put(flightNumber, data);

    }
}
