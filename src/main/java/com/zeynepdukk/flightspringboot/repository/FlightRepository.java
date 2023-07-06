package com.zeynepdukk.flightspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.zeynepdukk.flightspringboot.model.Flight;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
