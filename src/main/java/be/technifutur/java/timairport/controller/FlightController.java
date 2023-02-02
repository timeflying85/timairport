package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("flight/")
public class FlightController {

    @Autowired
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid FlightInsertForm form){

        flightService.createFlight( form );

    }




}
