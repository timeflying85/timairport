package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FirstOfficerRepository extends JpaRepository<Pilot, Long> {
}
