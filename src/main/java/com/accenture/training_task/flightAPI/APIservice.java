package com.accenture.training_task.flightAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class APIservice {

    @Value("${api.request}")
    private String requestURL;

    @Value("${limit}")
    private String limit;


    public FlightAPIResponse getObjectfromAPI(){
        RestTemplate restTemplate = new RestTemplate();
        FlightAPIResponse response = restTemplate.getForObject(
                requestURL  + limit, FlightAPIResponse.class);

        return response;
    }



}
