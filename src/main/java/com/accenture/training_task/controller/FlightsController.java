package com.accenture.training_task.controller;

import com.accenture.training_task.DataStorage;
import com.accenture.training_task.exceptions.FlightAlreadyExistsException;
import com.accenture.training_task.exceptions.FlightDoesNotExistException;
import com.accenture.training_task.flightAPI.APIservice;
import com.accenture.training_task.model.FlightData;
import com.accenture.training_task.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/api")
public class FlightsController {

    private static final Logger logger = LoggerFactory.getLogger(FlightsController.class);

    @Autowired
    private APIservice apIservice;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private DataStorage dataStorage;

    @Cacheable(value = "flights")
    @GetMapping("/flights")
    public String getApiResponse(Model model){
        logger.trace("GET flights method accessed");

        List<FlightData> flightDataList = apIservice.getFlightList();
        model.addAttribute("flights", flightDataList);
        return "flights";
    }


 /*   @GetMapping("/flights/{departureAirport}") //possibly implement search
    public List<Datum> getAllData(@PathVariable("departureAirport") String departureAirport) {
        return APIservice.getObjectfromAPIbyDepartureAirport(departureAirport).getData();
    }*/
    
    @GetMapping("/jpa/favorites")
    public String showFavorites(Model model){
        logger.trace("GET favourite flights method accessed");
        List<FlightData> flightDataList = dataRepository.findAll();
        model.addAttribute("flights", flightDataList);
        return "favorites";
    }


    @Deprecated
    @PostMapping("/favorites/add")
    public ResponseEntity<Object> addFavorite(@NotEmpty @RequestParam String flightNumber, @Valid @RequestBody FlightData body) {

        if (dataStorage.getData().containsKey(flightNumber))
        	throw new FlightAlreadyExistsException("Flight with number " + body.getFlightNumber() + " is already added to the favorites");

        dataStorage.addData(flightNumber, body);
        logger.trace("Add flight to local storage");
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/favorites").build().toUri();

        System.out.println(dataStorage.getData());
        return ResponseEntity.created(location).build();
    }
    
    @PostMapping("/jpa/favorites/add/{flightNumber}")
    public ResponseEntity<Object> addJPAFavorite(@PathVariable String flightNumber, @Valid @RequestBody FlightData body) {

    	if (dataRepository.findByFlightNumber(body.getFlightNumber()) != null)
    		throw new FlightAlreadyExistsException("Flight with number " + body.getFlightNumber() + " is already added to the favorites");

		body.setFlightNumber(flightNumber);
    	dataRepository.save(body);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/jpa/favorites").build().toUri();
        logger.trace("POST flight to H2");
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/favorites/remove/{flightNumber}")
    public ResponseEntity<Object> removeFavorite(@PathVariable String flightNumber) {

    	FlightData data = dataRepository.findByFlightNumber(flightNumber);

    	if (data == null) throw new FlightDoesNotExistException("This flight is not in your favorites.");

    	dataRepository.delete(data);
        logger.trace("DELETE flight from H2");
    	return ResponseEntity.ok().build();
    }

}
