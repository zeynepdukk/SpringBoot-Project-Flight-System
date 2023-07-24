package com.zeynepdukk.flightspringboot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeynepdukk.flightspringboot.controller.FlightController;
import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.repository.FlightRepository;
import com.zeynepdukk.flightspringboot.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
public class FlightControllerTest {
    @MockBean
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateFlight() throws Exception{
        Flight flight1 = new Flight();
        flight1.setDestination_city("istanbul");
        flight1.setCurrent_city("lisbon");
        flight1.setCapacity(BigDecimal.valueOf(123L));
        flightRepository.save(flight1);
        List<Flight> flightList =new ArrayList<>();
        flightList.add(flight1);


        mockMvc.perform(post("/flights").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flight1)))
                .andExpect(status().isOk())
                .andDo(print());


    }


    @Test
    void shouldReturnFlight() throws Exception {
        long id=0L ;
        Flight flight1 = new Flight();
        flight1.setDestination_city("istanbul");
        flight1.setCurrent_city("lisbon");
        flight1.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight1);
        List<Flight> flightList =new ArrayList<>();
        flightList.add(flight1);


        when(flightService.getFlightById(id)).thenReturn(flight1);
        mockMvc.perform(get("/flights/{id}",id)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.current_city").value(flight1.getCurrent_city()))
                .andExpect(jsonPath("$.destination_city").value(flight1.getDestination_city()))
                .andExpect(jsonPath("$.capacity").value(flight1.getCapacity()))
                .andExpect(jsonPath("$.createTime").value(flight1.getCreateTime()))
                .andDo(print());


    }

    @Test
    public void shouldReturnFlightList() throws Exception{
        List<Flight> flightList =new ArrayList<>();
        Flight flight1 = new Flight();
        flight1.setDestination_city("istanbul");
        flight1.setCurrent_city("lisbon");
        flight1.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight1);

        Flight flight2 = new Flight();
        flight2.setDestination_city("ist");
        flight2.setCurrent_city("ant");
        flight2.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight2);
        flightList.add(flight1);
        flightList.add(flight2);

        given(flightService.getAllFlight()).willReturn(flightList);

        ResultActions response =mockMvc.perform(get("/flights"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(flightList.size()))
                .andDo(print());


    }

    @Test
    void shouldDeleteFlight() throws Exception{
        long id=0L;
        doNothing().when(flightService).deleteFlight(id);
        mockMvc.perform(delete("/flights/{id}",id))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
    void shouldListByCreateTime() throws Exception{
        List<Flight> flightList =new ArrayList<>();
        Flight flight1 = new Flight();
        flight1.setDestination_city("istanbul");
        flight1.setCurrent_city("lisbon");
        flight1.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight1);

        Flight flight2 = new Flight();
        flight2.setDestination_city("ist");
        flight2.setCurrent_city("ant");
        flight2.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight2);
        flightList.add(flight1);
        flightList.add(flight2);

        given(flightService.getAllFlightsByCreatedTime()).willReturn(flightList);

        ResultActions response =mockMvc.perform(get("/flights/sortByCreatedTime"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()").value(flightList.size()))
                .andDo(print());



    }

    @Test
    void shouldPageFlights() throws Exception{
        int pageNum=2;
        List<Flight> flightList=new ArrayList<>();

        Flight flight1 = new Flight();
        flight1.setDestination_city("istanbul");
        flight1.setCurrent_city("lisbon");
        flight1.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight1);

        Flight flight2 = new Flight();
        flight2.setDestination_city("ist");
        flight2.setCurrent_city("ant");
        flight2.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight2);

        Flight flight3 = new Flight();
        flight3.setDestination_city("istanbul");
        flight3.setCurrent_city("lisbon");
        flight3.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight3);

        Flight flight4 = new Flight();
        flight4.setDestination_city("ist");
        flight4.setCurrent_city("ant");
        flight4.setCapacity(BigDecimal.valueOf(123L));
         flightService.saveFlight(flight4);
        flightList.add(flight1);
        flightList.add(flight2);
        flightList.add(flight3);
        flightList.add(flight4);
        Page<Flight> flightPage=new PageImpl<>(flightList);
        given(flightService.listAll(pageNum)).willReturn(flightPage);

        ResultActions response =mockMvc.perform(get("/page/{pageNum}",pageNum));

        response.andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void shouldSearchFlight() throws Exception {
        String query = "ist";
        List<Flight> flightList=new ArrayList<>();

        Flight flight1 = new Flight();
        flight1.setDestination_city("istanbul");
        flight1.setCurrent_city("lisbon");
        flight1.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight1);

        Flight flight2 = new Flight();
        flight2.setDestination_city("ist");
        flight2.setCurrent_city("ant");
        flight2.setCapacity(BigDecimal.valueOf(123L));
        flightService.saveFlight(flight2);

        flightList.add(flight1);
        flightList.add(flight2);


        given(flightService.searchFlight(query)).willReturn(flightList);

        ResultActions response=mockMvc.perform(get("/search?query=ist"));

        response.andExpect(status().isOk())
                .andDo(print());






    }
}