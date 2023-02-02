package be.technifutur.java.timairport.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

// permet de créer un repo "à la main"

public class CustomPilotRepository {

    @PersistenceContext
    private EntityManager manager;

}
