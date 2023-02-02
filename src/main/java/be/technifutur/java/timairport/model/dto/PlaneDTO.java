package be.technifutur.java.timairport.model.dto;


import be.technifutur.java.timairport.model.entity.Plane;
import be.technifutur.java.timairport.model.entity.TypePlane;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PlaneDTO {

    private Long id;

    private String callSign;

    private LocalDate registrationDate;

    private boolean inMaintenance;

    private TypeDTO type;

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

        private String originCountry;

    }

    public static PlaneDTO from(Plane entity) {

        if (entity == null)
            return null;

        return PlaneDTO.builder()
                .id( entity.getId() )
                .inMaintenance( entity.isInMaintenance() )
                .callSign( entity.getCallSign() )
                .registrationDate( entity.getRegistrationDate() )
                .company(
                        PlaneDTO.CompanyDTO.builder()
                                .id( entity.getCompany().getId() )
                                .name( entity.getCompany().getName() )
                                .originCountry( entity.getCompany().getOriginCountry() )
                                .build()
                )
                .type(
                        PlaneDTO.TypeDTO.builder()
                                .id( entity.getType().getId() )
                                .name( entity.getType().getName() )
                                .capacity( entity.getType().getCapacity() )
                                .build()
                )
                .build();

    }
}
