package com.accenture.training_task.controller;

import com.accenture.training_task.flightAPI.Datum;
import com.accenture.training_task.flightAPI.FlightAPIResponse;
import com.accenture.training_task.flightAPI.GetAPIObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private GetAPIObject getAPIObject;

    @RequestMapping("/")
    public String index() throws Exception {

        //Format an output
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightAPIResponse flightAPIResponse = getAPIObject.getObjectfromAPI();
        String jsonOutput = gson.toJson(getAPIObject.getObjectfromAPI());

        return jsonOutput;
    }

}
