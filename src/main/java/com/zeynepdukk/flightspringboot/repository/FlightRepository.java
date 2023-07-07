package com.zeynepdukk.flightspringboot.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.zeynepdukk.flightspringboot.model.Flight;

import java.math.BigDecimal;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    Page<Flight> findAll(Pageable pageable);

}
