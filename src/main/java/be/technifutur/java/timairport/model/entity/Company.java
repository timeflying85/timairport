package be.technifutur.java.timairport.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id", nullable = false)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "origin_country", nullable = false)
    private String originCountry;

    @OneToMany(mappedBy = "company")
    private List<Plane> planes;

    @OneToMany(mappedBy = "company")
    private List<Pilot> pilots;

    @OneToMany(mappedBy = "company")
    private Set<Flight> flights = new LinkedHashSet<>();


}
