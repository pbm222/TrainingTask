package com.accenture.training_task.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.training_task.DataRepository;
import com.accenture.training_task.DataStorage;
import com.accenture.training_task.FlightData;
import com.accenture.training_task.flightAPI.FlightAPIResponse;
import com.accenture.training_task.flightAPI.GetAPIObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
public class HelloController {

    @Autowired
    private GetAPIObject getAPIObject;
    
    @Autowired
    private DataRepository dataRepository;

    private DataStorage dataStorage = new DataStorage();

    @RequestMapping("/")
    public String index() throws Exception {

        //Format an output
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FlightAPIResponse flightAPIResponse = getAPIObject.getObjectfromAPI();
        String jsonOutput = gson.toJson(flightAPIResponse);

        return jsonOutput;
    }	

    @Deprecated
    @PostMapping("/addFavorite")
    public ResponseEntity<Object> addFavorite(@NotEmpty @RequestParam int flightNumber, @Valid @RequestBody FlightData body) {

        if (dataStorage.getData().containsKey(flightNumber)) throw new RuntimeException(); // TODO: FLIGHT EXISTS EXEPTION

        dataStorage.addData(flightNumber, body);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/favorites").build().toUri();
        
        System.out.println(dataStorage.getData());
        return ResponseEntity.created(location).build();
    }
    
    @PostMapping("/jpa/addFavorite")
    public ResponseEntity<Object> addJPAFavorite(@Valid @RequestBody FlightData body) {
    	dataRepository.findAll().forEach(data -> {
    		if (data.getFlightNumber().equals(body.getFlightNumber())) {    			
    			throw new RuntimeException(); // TODO: FLIGHT EXISTS EXEPTION
    		}
    	});
    	
    	dataRepository.save(body);
    	
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/favorites").build().toUri();
        
        return ResponseEntity.created(location).build();
    }

}
