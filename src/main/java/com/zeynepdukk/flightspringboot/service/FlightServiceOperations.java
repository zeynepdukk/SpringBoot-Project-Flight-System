package com.zeynepdukk.flightspringboot.service;
import  com.zeynepdukk.flightspringboot.exception.FlightNotFoundException;
import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.repository.FlightRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import  java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

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
        Optional<Flight> flight1=this.flightRepository.findById(flightId);
        if (flight1.isPresent()){
            this.flightRepository.delete(flight1.get());

        }
        else {
            throw new FlightNotFoundException("Flight not found");
        }
    }
}

