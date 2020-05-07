package com.accenture.training_task.flightAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GetAPIObject {

    @Value("${api.request}")
    private String requestURL;

    public FlightAPIResponse getObjectfromAPI(){
        RestTemplate restTemplate = new RestTemplate();
        FlightAPIResponse response = restTemplate.getForObject(
                requestURL, FlightAPIResponse.class);

        return response;
    }



}
