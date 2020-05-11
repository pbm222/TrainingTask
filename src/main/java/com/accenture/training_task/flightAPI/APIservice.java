package com.accenture.training_task.flightAPI;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.accenture.training_task.flightAPI.responseModel.Datum;
import com.accenture.training_task.flightAPI.responseModel.FlightAPIResponse;
import com.accenture.training_task.flightData.FlightData;

@Component
public class APIservice {

    @Value("${api.request}")
    private String requestURL;

    @Value("${limit}")
    private String limit;

    @Autowired
    private RestTemplate restTemplate;

    @Cacheable(value = "flights")
    public FlightAPIResponse getRawfromAPI() {
        FlightAPIResponse response = restTemplate.getForObject(
                requestURL + limit, FlightAPIResponse.class);

        return response;
    }

    public List<FlightData> getFlightList(){

        List<Datum> datumList = getRawfromAPI().getData();
        List<FlightData> flightDataList = new ArrayList<>();

        datumList.forEach(d -> {
            flightDataList.add(new FlightData(d.getDeparture().getAirport(),
                    d.getArrival().getAirport(), d.getAirline().getName(), d.getFlight().getNumber()));
        });

        return flightDataList;
    }

    @Cacheable(value = "flights")
    public FlightAPIResponse getObjectfromAPIbyDepartureAirport(String departureAirport) {
        FlightAPIResponse response = restTemplate.getForObject(
                requestURL + limit + "&dep_iata=", FlightAPIResponse.class);

        return response;
    }



}
