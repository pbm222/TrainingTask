package com.accenture.training_task.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

//import com.accenture.training_task.DataStorage;
import com.accenture.training_task.DataStorage;
import com.accenture.training_task.flightAPI.APIservice;
import com.accenture.training_task.exceptions.FlightAlreadyExistsException;
import com.accenture.training_task.model.FlightData;
import com.accenture.training_task.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("/api")
public class FlightsController {

    private static final Logger logger = LoggerFactory.getLogger(FlightsController.class);

    @Autowired
    private APIservice APIservice;

    @Autowired
    private DataRepository dataRepository;

    private DataStorage dataStorage = new DataStorage();

    @GetMapping("/flights")
    public String getApiResponse(Model model){
        logger.trace("GET flights method accessed");

        List<FlightData> flightDataList= APIservice.getFlightList();
        model.addAttribute("flights", flightDataList);
        return "flights";
    }


 /*   @GetMapping("/flights/{departureAirport}") //possibly implement search
    public List<Datum> getAllData(@PathVariable("departureAirport") String departureAirport) {
        return APIservice.getObjectfromAPIbyDepartureAirport(departureAirport).getData();
    }*/


    @Deprecated
    @PostMapping("/addFavorite")
    public ResponseEntity<Object> addFavorite(@NotEmpty @RequestParam int flightNumber, @Valid @RequestBody FlightData body) {

        if (dataStorage.getData().containsKey(flightNumber)) throw new RuntimeException(); // TODO: FLIGHT EXISTS EXEPTION

        dataStorage.addData(flightNumber, body);
        logger.trace("Add flight to local storage");
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/favorites").build().toUri();

        System.out.println(dataStorage.getData());
        return ResponseEntity.created(location).build();
    }
    
    @PostMapping("/jpa/addFavorite/{flightNumber}")
    public ResponseEntity<Object> addJPAFavorite(@PathVariable String flightNumber, @Valid @RequestBody FlightData body) {
    	
    	if (dataRepository.findByFlightNumber(body.getFlightNumber()) != null)
    		throw new FlightAlreadyExistsException("Flight with number " + body.getFlightNumber() + " is already added to the favorites");

		body.setFlightNumber(flightNumber);
    	dataRepository.save(body);
    	
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/favorites").build().toUri();
        logger.trace("POST flight to H2");
        return ResponseEntity.created(location).build();
    }

}
