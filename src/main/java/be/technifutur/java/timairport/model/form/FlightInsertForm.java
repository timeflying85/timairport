package be.technifutur.java.timairport.model.form;


import be.technifutur.java.timairport.model.entity.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightInsertForm {

    @NotNull
    private long id;

    @NotNull
    @Future
    private LocalDateTime departureTime;

    @NotNull
    @Future
    private LocalDateTime arrivalTime;

    @NotNull
    private long captain;

    @NotNull
    private long firstOfficer;

    @NotNull
    private long departureAirportId;

    @NotNull
    private long arrivalAirportId;

    @NotNull
    private long plane;

    @NotNull
    private long company;



    public Flight toEntity(){

        Flight flight = new Flight();

        flight.setDepartureTime( this.getDepartureTime() );
        flight.setArrivalTime( this.getArrivalTime() );
        flight.setCancelled(false);

        return flight;

    }


}
