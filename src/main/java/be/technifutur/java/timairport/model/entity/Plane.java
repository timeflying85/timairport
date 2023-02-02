package be.technifutur.java.timairport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plane_id", nullable = false)
    private long id;

    @Column(name = "call_sign", nullable = false, unique = true)
    private String callSign;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "in_maintenance", nullable = false)
    private boolean inMaintenance;

    @OneToOne(mappedBy = "plane")
    private Flight flights;

    @ManyToOne
    @JoinColumn(name = "plane_type", nullable = false)
    private TypePlane type;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Company company;


}
