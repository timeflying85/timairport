package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightService {

     void createFlight(@Valid FlightInsertForm form);

    FlightDTO getOne(long id);

    List<FlightDTO> getAll();

    void delete(long id);

    void updateDepartureTime(long id, LocalDateTime time);

    void updateArrivalTime(long id, LocalDateTime time);

}
