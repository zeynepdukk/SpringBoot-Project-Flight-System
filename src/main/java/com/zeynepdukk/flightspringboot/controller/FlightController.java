package com.zeynepdukk.flightspringboot.controller;

import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.service.FlightService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<List<Flight>> getAllFlight() {
        return ResponseEntity.ok().body(flightService.getAllFlight());
    }

    @GetMapping("/flights/sortByCreatedTime")
    public ResponseEntity<List<Flight>> getAllFlightsByCreatedTime() {
        return ResponseEntity.ok().body(flightService.getAllFlightsByCreatedTime());
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable long id) {
        return ResponseEntity.ok().body(flightService.getFlightById(id));
    }

    @PostMapping("/flights")
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        return ResponseEntity.ok().body(this.flightService.createFlight(flight));
    }

    @PutMapping("/flights/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable long id, Flight flight) {
        return ResponseEntity.ok().body(this.flightService.updateFlight(flight.setId(id)));

    }

    @DeleteMapping("/flights/{id}")
    public HttpStatus deleteProduct(@PathVariable long id) {
        this.flightService.deleteFlight(id);
        return HttpStatus.OK;
    }

    @GetMapping("/page/{pageNum}")
    public ResponseEntity<Page<Flight>> viewPage(@PathVariable(name="pageNum")int pageNum){
        Page<Flight> page=flightService.listAll(pageNum);
        List<Flight> flightList=page.getContent();

        return ResponseEntity.ok().body(flightService.listAll(pageNum));

    }

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlight(@RequestParam("query")String query){
        return ResponseEntity.ok().body(flightService.searchFlight(query));
    }



}



