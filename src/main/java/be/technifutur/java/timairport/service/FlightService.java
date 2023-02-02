package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.form.FlightInsertForm;
import jakarta.validation.Valid;

public interface FlightService {

     void createFlight(@Valid FlightInsertForm form);

}
