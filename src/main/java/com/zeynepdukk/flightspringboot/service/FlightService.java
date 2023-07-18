package com.zeynepdukk.flightspringboot.service;
import com.zeynepdukk.flightspringboot.model.Flight;
import org.springframework.data.domain.Page;

import  java.util.List;
public interface FlightService {
    Flight createFlight(Flight flight);
    Flight updateFlight(Flight flight);
    List<Flight> getAllFlight();
    List<Flight> getAllFlightsByCreatedTime();
    Flight getFlightById(long flightId);
    void deleteFlight(long id);
    List<Flight> searchFlight(String query);
    Page<Flight> listAll(int pageNum); //by pagination

    Flight saveFlight(Flight flight);

}
