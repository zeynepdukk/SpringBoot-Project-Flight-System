package com.zeynepdukk.flightspringboot.repository;

import com.zeynepdukk.flightspringboot.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    Page<Flight> findAll(Pageable pageable);


    @Query("SELECT f from Flight f WHERE "+ "f.current_city LIKE CONCAT ('%',:query,'%')")
    List<Flight> searchFlight(String query);

}
