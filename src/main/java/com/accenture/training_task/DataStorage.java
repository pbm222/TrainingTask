package com.accenture.training_task;

import com.accenture.training_task.flightData.FlightData;

import java.util.HashMap;

public class DataStorage {

    private HashMap<Integer, FlightData> data;

    public DataStorage() {
        this.data = new HashMap<Integer, FlightData>();
    }

    public HashMap<Integer, FlightData> getData() {
        return data;
    }

    public FlightData getSpecificData(Integer flightNumber) {
        return this.data.get(flightNumber);
    }

    public void addData(Integer flightNumber, FlightData data) {
        this.data.put(flightNumber, data);

    }
}
