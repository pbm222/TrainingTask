package com.accenture.training_task.flightAPI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paginition {

    private Integer limit;
    private Integer total;

    public Paginition(Integer limit, Integer total) {
        this.limit = limit;
        this.total = total;
    }

    public Paginition() {
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Paganition{" +
                "limit=" + limit +
                ", total=" + total;
    }
}
