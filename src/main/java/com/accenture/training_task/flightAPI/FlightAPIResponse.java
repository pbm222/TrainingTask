package com.accenture.training_task.flightAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightAPIResponse {

    private Paginition pagination;
    private List<Datum> data = null;

    public FlightAPIResponse(Paginition pagination, List<Datum> data) {
        this.pagination = pagination;
        this.data = data;
    }

    public FlightAPIResponse() {
    }

    public Paginition getPagination() {
        return pagination;
    }

    public void setPagination(Paginition pagination) {
        this.pagination = pagination;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "FlightAPIResponse{" +
                "pagination=" + pagination +
                ", data=" + data +
                '}';
    }
}

