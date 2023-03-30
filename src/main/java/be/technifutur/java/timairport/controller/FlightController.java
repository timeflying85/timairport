package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
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


    @GetMapping("/{id:[0-9]+}")
    public FlightDTO getOne(@PathVariable long id){

        return flightService.getOne(id);

    }


    @GetMapping("/all")
    public List<FlightDTO> getAll() {

        return flightService.getAll();

    }


    @GetMapping({"/{id:[0-9]+}/delete"})
    public void delete(@PathVariable long id){

        flightService.delete(id);

    }

    @PatchMapping(value = "/{id:[0-9]+}/update", params = "departureTime")
    public void updateDepartureTime(@PathVariable long id, @RequestParam("departureTime") LocalDateTime time) {

        flightService.updateDepartureTime(id,time);

    }

    @PatchMapping(value = "/{id:[0-9]+}/update", params = "arrivalTime")
    public void updateArrivalTime(@PathVariable long id, @RequestParam("arrivalTime") LocalDateTime time) {

        flightService.updateArrivalTime(id,time);

    }

//    @PutMapping("/{id:[0-9]+}/update")
//    public void updateFlight(@PathVariable long id, @RequestBody FlightInsertForm updatedFlight) {
//        flightService.updateFlight(updatedFlight);
//    }

//    @PutMapping("/{id}/update")
//    public ResponseEntity<FlightDTO> updateFlight(@PathVariable long id, @RequestBody FlightDTO flightDTO) {
//        FlightDTO updatedFlight = flightService.updateFlight();
//        return ResponseEntity.ok(updatedFlight);
//    }

    @PutMapping("/{id:[0-9]+}/update")
    public FlightDTO updateFlight(@PathVariable long id, @RequestBody FlightDTO flightDTO) {
        return flightService.updateFlight(id, flightDTO);
    }




}
