package be.technifutur.java.timairport.model.dto;


import be.technifutur.java.timairport.model.entity.Airport;
import be.technifutur.java.timairport.model.entity.Flight;
import be.technifutur.java.timairport.model.entity.Pilot;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightDTO {

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Airport departureAirportId;
    private Airport arrivalAirportId;
    private Pilot captainId;
    private Pilot firstOfficerId;
    private TypeDTO typePlane;
    private CompanyDTO company;



    @Data
    @Builder // pas nécessaire mais plus efficace avec
    public static class TypeDTO {  //class interne (une class à l'interieur d'une autre)

        private long id;

        private String name;

        private int capacity;

    }

    @Data
    @Builder // pas nécessaire mais plus efficace avec
    public static class CompanyDTO {

        private long id;

        private String name;


    }



    public static FlightDTO from(Flight entity) {

        if (entity == null)
            return null;

        return FlightDTO.builder()
                .company(
                        FlightDTO.CompanyDTO.builder()
                                .id( entity.getId() )
//                                .name( entity. )
                                .build()
                )
                .departureAirportId(entity.getDepartureAirportId())
                .arrivalAirportId(entity.getDestinationAirportId())
                .departureTime(entity.getDepartureTime())
                .arrivalTime(entity.getArrivalTime())
                .captainId(entity.getCaptain())
                .firstOfficerId(entity.getFirstOfficer())
                .build();

    }






}
