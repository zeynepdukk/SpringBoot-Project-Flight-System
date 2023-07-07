package com.zeynepdukk.flightspringboot.model;
import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name="flights")
public class Flight {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private long id;
    @Column(name="current_city")
    private String current_city;

    @Column(name="destination_city")
    private String destination_city;

    @Column(name="capacity")
    private BigDecimal capacity;


    public long getId() {
        return id;
    }

    public Flight setId(long id) {
        this.id = id;
        return null;
    }

    public String getCurrent_city() {
        return current_city;
    }

    public void setCurrent_city(String current_city) {
        this.current_city = current_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }
}
