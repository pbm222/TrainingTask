package com.accenture.training_task.controller;

import com.accenture.training_task.flightAPI.APIservice;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private APIservice APIservice;

    @GetMapping("/")
    public String toindex() {

        //Format an output
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(APIservice.getObjectfromAPI());

        return jsonOutput;
    }

}
