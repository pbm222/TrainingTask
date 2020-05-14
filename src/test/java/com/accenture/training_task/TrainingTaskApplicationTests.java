package com.accenture.training_task;

import com.accenture.training_task.controller.FlightsController;
import com.accenture.training_task.flightAPI.APIservice;
import com.accenture.training_task.model.FlightData;
import com.accenture.training_task.repository.DataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.nio.file.Files;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TrainingTaskApplicationTests {

    @Autowired
    private FlightsController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private APIservice apIservice;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    public void testFlightsPage() throws Exception {
        File flights = new ClassPathResource("templates/flights.html").getFile();
        String html = new String((Files.readAllBytes(flights.toPath())));

        Thread.sleep(2000);

        this.mockMvc.perform(get("/api/flights"))
                .andExpect(status().isOk())
                .andExpect(view().name("flights"))
                .andExpect(model().attributeExists("flights"))
                .andDo(print());
    }

    @Test
    public void addFlightToJPAFavorites() throws Exception {

        FlightData flightData = new FlightData("Riga Airport",
                "Copenhagen Kastrup airport", "Norwegian", String.valueOf(Math.random()));

        mockMvc.perform(
                post("/api/jpa/favorites/add/{flightNumber}", flightData.getFlightNumber())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(flightData)))
                .andExpect(status().isCreated());
    }

    @Test
    public void addFlightsToFavorites() throws Exception {

        FlightData flightData = new FlightData("Riga Airport",
                "Copenhagen Kastrup airport", "Norwegian", String.valueOf(Math.random()));
        String URL = "/api/favorites/add?flightNumber=" + flightData.getFlightNumber();

        mockMvc.perform(
                post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(flightData)))
                .andExpect(status().isCreated());
    }

}



