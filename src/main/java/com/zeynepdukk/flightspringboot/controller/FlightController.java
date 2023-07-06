package com.zeynepdukk.flightspringboot.controller;
import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController

public class FlightController {

    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity<List<Flight>> getAllFlight(){
        return ResponseEntity.ok().body(flightService.getAllFlight());
    }
    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id){
        return ResponseEntity.ok().body(flightService.getFlightById(id));
    }
    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        return ResponseEntity.ok().body(this.flightService.createFlight(flight));
    }
    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long id,Flight flight){
        return ResponseEntity.ok().body(this.flightService.updateFlight(flight.setId(id)));

    }
    @DeleteMapping("/flights/{id}")
    public HttpStatus deleteProduct(@PathVariable long id){
        this.flightService.deleteFlight(id);
        return HttpStatus.OK;
    }
}

