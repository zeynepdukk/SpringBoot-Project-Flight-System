package com.zeynepdukk.flightspringboot;

import com.zeynepdukk.flightspringboot.model.Flight;
import com.zeynepdukk.flightspringboot.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@DataJpaTest
public class JPAUnitTest {


        @Autowired
        private TestEntityManager entityManager;

        @MockBean
        FlightService flightService;

        @Test
        public void turnEmpty(){
            List<Flight> flight =flightService.getAllFlight();
            assertThat(flight).isEmpty();
        }
        @Test
        public void shouldCreateFlight(){
            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);

            List<Flight> flightList =new ArrayList<>();
            flightList.add(flight1);

            entityManager.persist(flight1);
            assertNotNull(flight1.getId());
            assertThat(flight1).isNotNull();
        }

        @Test
        public void shouldListFlights(){
            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);
            entityManager.persist(flight1);

            Flight flight2 = new Flight();
            flight2.setDestination_city("istanbul");
            flight2.setCurrent_city("lisbon");
            flight2.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight2);
            entityManager.persist(flight2);

            Flight flight3 = new Flight();
            flight3.setDestination_city("istanbul");
            flight3.setCurrent_city("lisbon");
            flight3.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight3);
            entityManager.persist(flight3);

            List<Flight> flightList =(List<Flight>) flightService.getAllFlight();
            flightList.add(flight1);
            flightList.add(flight2);
            flightList.add(flight3);

            assertThat(flightList).isNotNull();
            assertThat(flightList.size()).isEqualTo(3);

        }


        @Test
        public void shouldReturnFlight(){
            /*
            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);
            entityManager.persist(flight1);

            Flight flight2 = new Flight();
            flight2.setDestination_city("istanbul");
            flight2.setCurrent_city("lisbon");
            flight2.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight2);
            entityManager.persist(flight2);

            List<Flight> flightList1 =new ArrayList<>();
            flightList1.add(flight1);

            List<Flight> flightList =(List<Flight>) flightService.getFlightById(flight1.getId());
            flightList.add(flight1);

            assertThat(flightList).isEqualTo(flightList1);

            /*
            flightList1.add(flight2);

            //List<Flight> flightList =new ArrayList<>();
           // flightList.add(FlightService.getFlightById(flight2.getId()));



            Flight flight=flightService.getFlightById(flight2.getId());
            assertThat(flight.getId()).isEqualTo(flight2.getId());



 */
           // Flight flight=flightService.getFlightById(1L);
           // Assertions.assertThat(flight.getId()).isEqualTo(1L);
            long id=0L ;
            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);
            List<Flight> flightList =new ArrayList<>();
            flightList.add(flight1);


            when(flightService.getFlightById(id)).thenReturn(flight1);



        }

        @Test
        public void shouldDeleteFlight(){
            long id=0L ;
            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);
            List<Flight> flightList =new ArrayList<>();
            flightList.add(flight1);

            flightService.deleteFlight(flight1.getId());

            List<Flight> flight =flightService.getAllFlight();

            assertThat(flight).hasSize(0);

        }


        @Test
        public void shouldListByCreateTime(){
            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);
            entityManager.persist(flight1);

            Flight flight2 = new Flight();
            flight2.setDestination_city("istanbul");
            flight2.setCurrent_city("lisbon");
            flight2.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight2);
            entityManager.persist(flight2);

            List<Flight> flightList =(List<Flight>) flightService.getAllFlightsByCreatedTime();
            flightList.add(flight1);
            flightList.add(flight2);

            assertThat(flightList.size()).isEqualTo(2);

        }

        @Test
        public void shouldPageFlights(){
            int pageNum=2;

            Flight flight1 = new Flight();
            flight1.setDestination_city("istanbul");
            flight1.setCurrent_city("lisbon");
            flight1.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight1);
            entityManager.persist(flight1);

            Flight flight2 = new Flight();
            flight2.setDestination_city("istanbul");
            flight2.setCurrent_city("lisbon");
            flight2.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight2);
            entityManager.persist(flight2);

            Flight flight3 = new Flight();
            flight3.setDestination_city("istanbul");
            flight3.setCurrent_city("lisbon");
            flight3.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight3);

            Flight flight4 = new Flight();
            flight4.setDestination_city("ist");
            flight4.setCurrent_city("ant");
            flight4.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight4);

            List<Flight> flightList=new ArrayList<>();
            flightList.add(flight1);
            flightList.add(flight2);
            flightList.add(flight3);
            flightList.add(flight4);
            Page<Flight> flightPage=new PageImpl<>(flightList, PageRequest.of(2,2), 4);
            when(flightService.listAll(pageNum)).thenReturn(flightPage);


            assertThat(flightService.listAll(pageNum).getSize()).isEqualTo(2);


        }

        @Test
        public void shouldSearchFlight(){
            String query = "ist";
            List<Flight> flightList=new ArrayList<>();

            Flight flight2 = new Flight();
            flight2.setDestination_city("ist");
            flight2.setCurrent_city("ant");
            flight2.setCapacity(BigDecimal.valueOf(123L));
            flightService.saveFlight(flight2);


            flightList.add(flight2);

            when(flightService.searchFlight(query)).thenReturn(flightList);
            assertThat(flightList).hasSize(1).contains(flight2);



        }

    }

