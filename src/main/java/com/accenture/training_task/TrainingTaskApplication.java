package com.accenture.training_task;

import com.accenture.training_task.flightAPI.FlightAPIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TrainingTaskApplication {

	private static final Logger log = LoggerFactory.getLogger(TrainingTaskApplication.class);

	@Value("${api.request}")
	private String requestURL;

	public static void main(String[] args) {
		SpringApplication.run(TrainingTaskApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			 FlightAPIResponse quote = restTemplate.getForObject(
					requestURL, FlightAPIResponse.class);
			log.info(quote.toString());
		};
	}




}
