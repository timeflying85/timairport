package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.exceptions.InvalidInputException;
import be.technifutur.java.timairport.exceptions.NoPlaneAvailableException;
import be.technifutur.java.timairport.exceptions.RessourceNotFoundException;
import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
            Flight flight = new Flight();
            flight.setDepartureTime(form.getDepartureTime());
            flight.setArrivalTime(form.getArrivalTime());
            flight.setDepartureAirportId(
                    airportRepository.findById(form.getDepartureAirportId()).orElseThrow()
                    );
            flight.setDestinationAirportId(
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

    }


