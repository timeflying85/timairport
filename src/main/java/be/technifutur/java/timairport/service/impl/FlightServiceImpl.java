package be.technifutur.java.timairport.service.impl;

import be.technifutur.java.timairport.exceptions.InvalidInputException;
import be.technifutur.java.timairport.exceptions.NoPlaneAvailableException;
import be.technifutur.java.timairport.exceptions.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.*;
import be.technifutur.java.timairport.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Service
    public class FlightServiceImpl implements FlightService {
        @Autowired
        private FlightRepository flightRepository;

        @Autowired
        private PlaneRepository planeRepository;

        @Autowired
        private CaptainRepository captainRepository;

        @Autowired
        private FirstOfficerRepository firstOfficerRepository;

        @Autowired
        private CompanyRepository companyRepository;

        @Autowired
        private AirportRepository airportRepository;

        @Override
        public void createFlight(FlightInsertForm form) {


            Airport departureAirport = airportRepository.findById(form.getDepartureAirportId())
                    .orElseThrow(RessourceNotFoundException::new);

            Airport destinationAirport = airportRepository.findById(form.getArrivalAirportId())
                    .orElseThrow(RessourceNotFoundException::new);


            // Vérifier si l'avion n'est pas en maintenance
            Plane plane = planeRepository.findById(form.getPlane())
                    .orElseThrow(RessourceNotFoundException::new);
            if (plane.isInMaintenance()) {
                try {
                    throw new NoPlaneAvailableException("The selected plane is in maintenance.");
                } catch (NoPlaneAvailableException e) {
                    throw new RuntimeException(e);
                }
            }

            // verifier si le type d'avion est autorisé dans les airport de départ et d'arrivée
            if (!departureAirport.getPlaneTypesAllowed().contains(plane.getType())) {
                try {
                    throw new NoPlaneAvailableException("The selected plane type is not accepted in the departure airport.");
                } catch (NoPlaneAvailableException e) {
                    throw new RuntimeException(e);
                }
            }

            if (!destinationAirport.getPlaneTypesAllowed().contains(plane.getType())) {
                try {
                    throw new NoPlaneAvailableException("The selected plane type is not accepted in the destination airport.");
                } catch (NoPlaneAvailableException e) {
                    throw new RuntimeException(e);
                }
            }



            // Vérifier si le capitaine et le firstOfficer sont différents
            Long captainId = form.getCaptain();
            Long firstOfficerId = form.getFirstOfficer();
            if (captainId.equals(firstOfficerId)) {
                try {
                    throw new InvalidInputException("The captain and the first officer cannot be the same person.");
                } catch (InvalidInputException e) {
                    throw new RuntimeException(e);
                }
            }

            // Vérifier si le capitaine et le firstOfficer existent dans la base de données
            Pilot captain = captainRepository.findById(captainId)
                    .orElseThrow(RessourceNotFoundException::new);
            Pilot firstOfficer = firstOfficerRepository.findById(firstOfficerId)
                    .orElseThrow(RessourceNotFoundException::new);

            // Vérifier si la compagnie existe dans la base de données
            Company company = companyRepository.findById(form.getCompany())
                    .orElseThrow(RessourceNotFoundException::new);

            // Enregistrer le nouveau vol dans la base de données
            Flight flight = form.toEntity();
            flight.setDepartureTime(form.getDepartureTime());
            flight.setArrivalTime(form.getArrivalTime());
            flight.setDeparture(
                    airportRepository.findById(form.getDepartureAirportId()).orElseThrow()
                    );
            flight.setDestination(
                    airportRepository.findById(form.getArrivalAirportId()).orElseThrow()
                    );
            flight.setCaptain(
                    captainRepository.findById(form.getCaptain()).orElseThrow()
                    );
            flight.setFirstOfficer(
                    firstOfficerRepository.findById(form.getFirstOfficer()).orElseThrow()
                    );
            flight.setPlane(
                    planeRepository.findById(form.getPlane()).orElseThrow()
                    );


            Flight savedFlight = flightRepository.save(flight);

            FlightDTO.from(savedFlight);
        }


        @Override
        public FlightDTO getOne(long id) {

            return flightRepository.findById(id)
                    .map( FlightDTO::from )
                    .orElseThrow( RessourceNotFoundException::new );

        }

        @Override
        public List<FlightDTO> getAll(){

            return flightRepository.findAll().stream().map( FlightDTO::from ).toList();

        }

        @Override
        public void delete(long id) {

            flightRepository.deleteById(id);

        }


        @Override
        public void updateDepartureTime(long id, LocalDateTime time) {

            if ( !flightRepository.existsById(id) ) {
                throw new RessourceNotFoundException();
            }

            flightRepository.updateDepartureTime(id, time);

        }

        @Override
        public void updateArrivalTime(long id, LocalDateTime time) {

            if ( !flightRepository.existsById(id) ) {
                throw new RessourceNotFoundException();
            }

            flightRepository.updateArrivalTime(id, time);

        }





    }


