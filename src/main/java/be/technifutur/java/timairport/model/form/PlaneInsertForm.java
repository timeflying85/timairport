package be.technifutur.java.timairport.model.form;


import be.technifutur.java.timairport.constraints.DayInPast;
import be.technifutur.java.timairport.constraints.Not0;
import be.technifutur.java.timairport.model.entity.Plane;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
public class PlaneInsertForm {

    @NotNull
    @Pattern(regexp = "[A-Z]-[A-Z]{4}|[A-Z]{2}-[A-Z]{3}|N[0-9]{1,5}[A-Z]{0,2}")
    private String callSign;

    @NotNull
    @DayInPast(value = 7, unit = ChronoUnit.DAYS)
    private LocalDate registrationDate;

    @NotNull
    @Not0
    private Long companyId;

    @NotNull
    private Long typeId;

    @NotNull
    public Plane toEntity(){

        Plane plane = new Plane();

        plane.setCallSign( this.getCallSign() );
        plane.setRegistrationDate( this.getRegistrationDate() );

        return plane;

    }



}
