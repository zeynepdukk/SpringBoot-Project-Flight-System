package com.zeynepdukk.flightspringboot.service;
import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.service.FlightService;
import org.springframework.data.domain.Page;

import  java.util.List;
public interface FlightService {
    Flight createFlight(Flight flight);
    Flight updateFlight(Flight flight);
    List<Flight> getAllFlight();
    Flight getFlightById(long flightId);
    void deleteFlight(long id);

    Page<Flight> listAll(int pageNum);

}
