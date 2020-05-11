package com.accenture.training_task.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.xml.transform.sax.SAXResult;

import com.accenture.training_task.exceptions.NoFlightsException;
import com.accenture.training_task.flightAPI.APIservice;
import com.accenture.training_task.flightAPI.responseModel.Datum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.accenture.training_task.repository.DataRepository;
import com.accenture.training_task.DataStorage;
import com.accenture.training_task.model.FlightData;

@Controller
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

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

        if (dataStorage.getData().containsKey(flightNumber))
            throw new RuntimeException(); // TODO: FLIGHT EXISTS EXEPTION

        dataStorage.addData(flightNumber, body);
        logger.trace("Add flight to local storage");
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/favorites").build().toUri();

        System.out.println(dataStorage.getData());
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/jpa/addFavorite/{flightnumber}")
    public ResponseEntity<Object> addJPAFavorite(@PathVariable String flightnumber, @Valid @RequestBody FlightData body) {
        dataRepository.findAll().forEach(data -> {
            if (data.getFlightnumber().equals(body.getFlightnumber())) {
                throw new RuntimeException(); // TODO: FLIGHT EXISTS EXEPTION
            }
        });

        body.setFlightnumber(flightnumber);
        dataRepository.save(body);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/jpa/favorites").build().toUri();
        logger.trace("POST flight to H2");
        return ResponseEntity.created(location).build();
    }


}
