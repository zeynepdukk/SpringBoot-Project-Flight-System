package com.zeynepdukk.flightspringboot.service;

import com.zeynepdukk.flightspringboot.exception.FlightNotFoundException;
import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.lang.System.currentTimeMillis;

@Service
@Qualifier("flightService")
@Transactional
public class FlightServiceOperations implements FlightService{

    public FlightServiceOperations(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private FlightRepository flightRepository;

    @Override
    public Flight createFlight(Flight flight) {
        flight.setCreateTime(currentTimeMillis());
        return flightRepository.save(flight);
    }

    @Override
    public Flight updateFlight(Flight flight) {
        Optional<Flight> flight1=this.flightRepository.findById((flight.getId()));
        if(flight1.isPresent()){
            Flight flightUpdate= flight1.get();;
            flightUpdate.setId(flight.getId());
            flightUpdate.setCurrent_city(flight.getCurrent_city());
            flightUpdate.setDestination_city(flight.getDestination_city());
            flightUpdate.setCapacity(flight.getCapacity());
            flightRepository.save(flightUpdate);
            return flightUpdate;
        }
        else {
            throw new FlightNotFoundException("Flight not found");
        }
    }

    @Override
    public List<Flight> getAllFlight() {
        return this.flightRepository.findAll();
    }

    @Override
    public List<Flight> getAllFlightsByCreatedTime() {
        return this.flightRepository.findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Override
    public Flight getFlightById(long flightId) {
        Optional<Flight> flight1=this.flightRepository.findById(flightId);
        if(flight1.isPresent()){
            return flight1.get();
        }
        else {
            throw new FlightNotFoundException("Flight not found");
        }
    }

    @Override
    public void deleteFlight(long flightId) {
        Optional<Flight> flight1 = this.flightRepository.findById(flightId);
        if (flight1.isPresent()) {
            this.flightRepository.delete(flight1.get());

        } else {
            throw new FlightNotFoundException("Flight not found");
        }
    }
    @Override
    public Page<Flight> listAll(int pageNum){
        int pageSize=2;
        Pageable pageable = PageRequest.of(pageNum-1,pageSize);
        return flightRepository.findAll(pageable);
    }

    @Override
    public List<Flight> searchFlight(String query){
        List<Flight> flights=flightRepository.searchFlight(query);
        return flights;
    }

    @Override
    public Flight saveFlight(Flight flight){
        Optional<Flight> savedFlight=this.flightRepository.findById(flight.getId());
        if(savedFlight.isPresent()){
            throw new FlightNotFoundException("Flight already exists");

        }
        return flightRepository.save(flight);
        }
    }



