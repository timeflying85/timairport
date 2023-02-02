package be.technifutur.java.timairport.model.dto;


import be.technifutur.java.timairport.model.entity.Airport;
import be.technifutur.java.timairport.model.entity.Flight;
import be.technifutur.java.timairport.model.entity.Pilot;
import be.technifutur.java.timairport.model.entity.Plane;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightDTO {

    private long id;
    private AirportDTO departure;
    private AirportDTO destination;
    private PilotDTO captain;
    private PilotDTO firstOfficer;
    private PlaneDTO plane;

    @Data
    @Builder
    private static class AirportDTO {
        private long id;
        private String name;
        private String location;

        public static AirportDTO from(Airport entity){
            if( entity == null )
                return null;

            return AirportDTO.builder()
                    .id( entity.getId() )
                    .name(entity.getName())
                    .location( String.format("%s,\n%s, %s", entity.getAddress(), entity.getCity(), entity.getCountry()) )
                    .build();
        }
    }

    @Data
    @Builder
    private static class PlaneDTO {
        private long id;
        private String callSign;
        private int capacity;

        public static PlaneDTO from(Plane entity){
            if( entity == null )
                return null;

            return PlaneDTO.builder()
                    .id( entity.getId() )
                    .callSign( entity.getCallSign() )
                    .capacity( entity.getType().getCapacity() )
                    .build();
        }
    }

    public static FlightDTO from(Flight entity){

        if( entity == null )
            return null;

        return FlightDTO.builder()
                .id( entity.getId() )
                .captain( PilotDTO.from( entity.getCaptain() ) )
                .firstOfficer( PilotDTO.from(entity.getFirstOfficer() ) )
                .departure( AirportDTO.from(entity.getDeparture() ) )
                .destination( AirportDTO.from(entity.getDestination()) )
                .plane( PlaneDTO.from( entity.getPlane() ) )
                .build();

    }

//@Data
//@Builder
//public class FlightDTO {
//
//    private LocalDateTime departureTime;
//    private LocalDateTime arrivalTime;
//    private Airport departureAirportId;
//    private Airport arrivalAirportId;
//    private PilotDTO captainId;
//    private PilotDTO firstOfficerId;
//    private TypeDTO typePlane;
//    private CompanyDTO company;
//
//
//
//    @Data
//    @Builder // pas nécessaire mais plus efficace avec
//    public static class TypeDTO {  //class interne (une class à l'interieur d'une autre)
//
//        private long id;
//
//        private String name;
//
//        private int capacity;
//
//    }
//
//    @Data
//    @Builder // pas nécessaire mais plus efficace avec
//    public static class CompanyDTO {
//
//        private long id;
//
//        private String name;
//
//
//    }
//
//    @Data
//    @Builder
//    public static class PilotDTO {
//
//        private long id;
//
//        private String name;
//
//
//    }
//
//
//
//    public static FlightDTO from(Flight entity) {
//
//        if (entity == null)
//            return null;
//
//        return FlightDTO.builder()
//                .company(
//                        FlightDTO.CompanyDTO.builder()
//                                .id( entity.getId() )
////                                .name( entity. )
//                                .build()
//                )
//                .departureAirportId(entity.getDepartureAirportId())
//                .arrivalAirportId(entity.getDestinationAirportId())
//                .departureTime(entity.getDepartureTime())
//                .arrivalTime(entity.getArrivalTime())
//                .captainId(
//                        PilotDTO.builder()
//                                .id( entity.getCaptain().getId() )
//                                .name( entity.getCaptain().getFirstName() + entity.getCaptain() )
//                                .build()
//
//                )
//                .firstOfficerId(entity.getFirstOfficer())
//                .build();
//
//    }
//





}
