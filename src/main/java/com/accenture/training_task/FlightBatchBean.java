package com.accenture.training_task;

import com.accenture.training_task.flightAPI.APIservice;
import com.accenture.training_task.flightAPI.Datum;
import com.accenture.training_task.flightAPI.FlightAPIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class FlightBatchBean {

    private static final Logger logger = LoggerFactory.getLogger(FlightBatchBean.class);

    @Autowired
    private APIservice apIservice;

    @Value("${api.request}")
    private String requestURL;

    @Value("${limit}")
    private String limit;

    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void cronJob() {
        RestTemplate restTemplate = new RestTemplate();
        FlightAPIResponse flightAPIResponse = restTemplate.getForObject(requestURL + limit, FlightAPIResponse.class);
        List<Datum> datumList = flightAPIResponse.getData(); //FIX: Can produce NUllPointerException

        logger.info(">CronJob started:");
        logger.info("Total flights: " + flightAPIResponse.getPagination().getTotal() + ", limit: "
        + flightAPIResponse.getPagination().getLimit());
        datumList.forEach(datum -> logger.info(datum.toString()));
        logger.info("< CronJob finished:");
    }

}
