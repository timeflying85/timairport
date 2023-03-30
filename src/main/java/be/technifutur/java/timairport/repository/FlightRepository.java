package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {


    @Modifying
    @Transactional // utile dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Flight f SET f.departureTime = ?2 WHERE f.id = ?1")
    void updateDepartureTime(long id, LocalDateTime time);


    @Modifying
    @Transactional // utile dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Flight f SET f.arrivalTime = ?2 WHERE f.id = ?1")
    void updateArrivalTime(long id, LocalDateTime time);


//    @Modifying
//    @Transactional
//    @Query("UPDATE Flight f SET f.departure = :#{#updatedFlight.departure}, f.destination = :#{#updatedFlight.destination}, f.departureTime = :#{#updatedFlight.departureTime}, f.arrivalTime = :#{#updatedFlight.arrivalTime}, f.captain = :#{#updatedFlight.captain}, f.copilot = :#{#updatedFlight.copilot}, f.plane = :#{#updatedFlight.plane} WHERE f.id = :#{#updatedFlight.id}")
//    void updateFlight(@Param("updatedFlight") FlightDTO updatedFlight);

    @Modifying
    @Transactional
    @Query("UPDATE Flight f SET f.departureTime = :#{#flight.departureTime}, f.arrivalTime = :#{#flight.arrivalTime}, " +
            "f.departure.id = :#{#flight.departure.id}, f.destination.id = :#{#flight.destination.id}, " +
            "f.captain.id = :#{#flight.captain.id}, f.firstOfficer.id = :#{#flight.firstOfficer.id}, " +
            "f.plane.id = :#{#flight.plane.id} WHERE f.id = :#{#flight.id}")
    void updateFlight(@Param("flight") Flight flight);


}

