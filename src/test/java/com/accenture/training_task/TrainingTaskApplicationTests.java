package com.accenture.training_task;

import com.accenture.training_task.controller.FlightsController;
import com.accenture.training_task.model.FlightData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingTaskApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private FlightsController controller;

    @Autowired
    private TestRestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFlightsPage() throws Exception {
        File flights = new ClassPathResource("flights.html").getFile();
        String html = new String((Files.readAllBytes(flights.toPath())));

        this.mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(content().string(html))
                .andDo(print());
    }

    @Test
    public void addFlightToFavourites() throws Exception {

		FlightData flightData = new FlightData("Riga Airport", "Copenhagen Kastrup airport", "Norwegian", "F3456");
        mockMvc.perform(post("/api/jpa/addFavorite/{flightNumber}", flightData.getFlightNumber())
				.content(objectMapper.writeValueAsString(flightData)))
				.andExpect(status().isOk());
    }



}

