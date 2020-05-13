package com.accenture.training_task.flightAPI;

import com.accenture.training_task.model.FlightData;
import com.accenture.training_task.exceptions.NoFlightsException;
import com.accenture.training_task.flightAPI.responseModel.Datum;
import com.accenture.training_task.flightAPI.responseModel.FlightAPIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class APIservice {

    private static final Logger logger = LoggerFactory.getLogger(APIservice.class);

    @Value("${api.request}")
    private String requestURL;

    @Value("${limit}")
    private String limit;

    @Autowired
    private RestTemplate restTemplate;

    private FlightAPIResponse flightAPIResponse;

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void cronJob() {

        try {
            FlightAPIResponse flightAPIResponse = restTemplate.getForObject(requestURL
                    + limit, FlightAPIResponse.class);
            this.flightAPIResponse = flightAPIResponse;


            List<Datum> datumList = flightAPIResponse.getData();
            logger.info(">CronJob started (getting data from API):");
            logger.info("Total flights: " + flightAPIResponse.getPagination().getTotal() + ", limit: "
                    + flightAPIResponse.getPagination().getLimit());
            datumList.forEach(datum -> logger.info(datum.toString()));
            logger.info("< CronJob finished:");

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
    }

    public List<FlightData> getFlightList() {
        List<FlightData> flightDataList = new ArrayList<>();
        try {
            List<Datum> datumList = flightAPIResponse.getData();
            datumList.forEach(d -> {
                flightDataList.add(new FlightData(d.getDeparture().getAirport(),
                        d.getArrival().getAirport(), d.getAirline().getName(), d.getFlight().getNumber()));
            });
        }catch (NullPointerException e){
            throw new NoFlightsException("No flights' list available!");
        }
            return flightDataList;
    }


   /* @Cacheable(value = "flights")
    public FlightAPIResponse getObjectfromAPIbyDepartureAirport(String departureAirport) {
        FlightAPIResponse response = restTemplate.getForObject(
                requestURL + limit + "&dep_iata=", FlightAPIResponse.class);

        return response;
    }*/

    public FlightAPIResponse getFlightAPIResponse() {
        return flightAPIResponse;
    }
}
