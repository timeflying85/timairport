package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Company;
import be.technifutur.java.timairport.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface FlightRepository extends JpaRepository<Flight, Long> {


    @Modifying
    @Transactional // utile dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Flight f SET f.departureTime = ?2 WHERE f.id = ?1")
    void updateDepartureTime(long id, LocalDateTime time);


    @Modifying
    @Transactional // utile dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Flight f SET f.arrivalTime = ?2 WHERE f.id = ?1")
    void updateArrivalTime(long id, LocalDateTime time);



}
