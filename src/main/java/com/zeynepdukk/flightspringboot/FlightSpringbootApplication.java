package com.zeynepdukk.flightspringboot;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;


import java.util.List;

@SpringBootApplication
public class FlightSpringbootApplication {

    public static void main(String[] args) {

        SpringApplication.run(FlightSpringbootApplication.class, args);
    }
}
