package com.zeynepdukk.flightspringboot.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus
public class FlightNotFoundException extends RuntimeException{

    private static final long serialVersionUID=1L;

    public FlightNotFoundException(String message){
        super(message);
    }
    public FlightNotFoundException(String message,Throwable throwable){
        super(message,throwable);
    }

}
