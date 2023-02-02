package be.technifutur.java.timairport.utils;

import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataInit implements InitializingBean {

    private final CompanyRepository companyRepository;
    private final TypePlaneRepository typePlaneRepository;

    private final FlightRepository flightRepository;

    private final PilotRepository pilotRepository;

    private final PlaneRepository planeRepository;

    private final AirportRepository airportRepository;


    public DataInit(CompanyRepository companyRepository, TypePlaneRepository typePlaneRepository, FlightRepository flightRepository, PilotRepository pilotRepository, PlaneRepository planeRepository, AirportRepository airportRepository) {
        this.companyRepository = companyRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.flightRepository = flightRepository;
        this.pilotRepository = pilotRepository;
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
    }


    @Override
    public void afterPropertiesSet() throws Exception {


        TypePlane type = new TypePlane();
        type.setName("heavy_plane");
        type.setCapacity(300);

        type = typePlaneRepository.save(type);

        TypePlane type2 = new TypePlane();
        type2.setName("medium_plane");
        type2.setCapacity(200);

        type2 = typePlaneRepository.save(type2);

        TypePlane type3 = new TypePlane();
        type3.setName("light_plane");
        type3.setCapacity(100);

        type3 = typePlaneRepository.save(type3);

        List<TypePlane> types = List.of(type,type2,type3);
        List<TypePlane> types2 = List.of(type3);
        List<TypePlane> types3 = List.of(type2,type3);

        Company company = new Company();
        company.setName("Big money company");
        company.setOriginCountry("USA");

        company = companyRepository.save(company);

        Company company2 = new Company();
        company2.setName("Deedlamerd");
        company2.setOriginCountry("Belgium");

        company2 = companyRepository.save(company2);

        Airport airport = new Airport();
        airport.setName("Pampano");
        airport.setCountry("USA");
        airport.setCity("Fort Lauderdale");
        airport.setPlaneParking(27);
        airport.setPlaneTypesAllowed(types3);

        airport = airportRepository.save(airport);

        Airport airport2 = new Airport();
        airport2.setName("vancity");
        airport2.setCountry("Canada");
        airport2.setCity("Vancouver");
        airport2.setPlaneParking(12);
        airport2.setPlaneTypesAllowed(types2);

        airport2 = airportRepository.save(airport2);

        Airport airport3 = new Airport();
        airport3.setName("ChamoLand");
        airport3.setCountry("France");
        airport3.setCity("Chamonix");
        airport3.setPlaneParking(3);
        airport3.setPlaneTypesAllowed(types);

        airport3 = airportRepository.save(airport3);

        Pilot pilot = new Pilot();
        pilot.setLicenseId("BEFCL0536A");
        pilot.setCompany(company);
        pilot.setLicenseAcquisition(LocalDate.from(LocalDateTime.of(2020,3,15,12,00)));
        pilot.setAddress("Rue de la légende 39, Suisse");
        pilot.setEmail("ElAviator@Hotmail.com");
        pilot.setFirstName("Marc");
        pilot.setLastName("Antoine");
        pilot.setPhone("+33492585902");

        pilot = pilotRepository.save(pilot);

        Pilot pilot2 = new Pilot();
        pilot2.setLicenseId("OOFYL0536A");
        pilot2.setCompany(company);
        pilot2.setLicenseAcquisition(LocalDate.from(LocalDateTime.of(2018,6,30,8,00)));
        pilot2.setAddress("Boulevard du ciel 12, France");
        pilot2.setEmail("CaptainKing@gmail.com");
        pilot2.setFirstName("Tim");
        pilot2.setLastName("Prega");
        pilot2.setPhone("+32472859614");

        pilot2 = pilotRepository.save(pilot2);

        Pilot pilot3 = new Pilot();
        pilot3.setLicenseId("NGTA5879WY");
        pilot3.setCompany(company2);
        pilot3.setLicenseAcquisition(LocalDate.from(LocalDateTime.of(2019,11,1,15,0)));
        pilot3.setAddress("Impasse du bonheur 5, Belgique");
        pilot3.setEmail("FlyingGuy@outlook.com");
        pilot3.setFirstName("Mateo");
        pilot3.setLastName("Nani");
        pilot3.setPhone("+82479459714");

        pilot3 = pilotRepository.save(pilot3);

        Plane plane = new Plane();
        plane.setCallSign("F-GUVM");
        plane.setCompany(company);
        plane.setRegistrationDate(LocalDate.of(2008,9,7));
        plane.setInMaintenance(false);
        plane.setType(type2);

        plane = planeRepository.save(plane);

        Plane plane2 = new Plane();
        plane2.setCallSign("F-HABM");
        plane2.setCompany(company);
        plane2.setRegistrationDate(LocalDate.of(2014,5,10));
        plane2.setInMaintenance(false);
        plane2.setType(type);

        plane2 = planeRepository.save(plane2);

        Flight flight = new Flight();
        flight.setId(1);
        flight.setArrivalTime(LocalDateTime.of(2023, 1, 28, 12, 00));
        flight.setDepartureTime(LocalDateTime.of(2023, 1, 29, 06, 00));
        flight.setPlane(plane);
        flight.setDestination(airport);
        flight.setDeparture(airport2);
        flight.setFirstOfficer(pilot);
        flight.setCaptain(pilot2);

        flight = flightRepository.save(flight);

        Flight flight2 = new Flight();
        flight2.setId(2);
        flight2.setArrivalTime(LocalDateTime.of(2023, 2, 12, 5, 30));
        flight2.setDepartureTime(LocalDateTime.of(2023, 2, 13, 1, 0));
        flight2.setPlane(plane2);
        flight2.setDestination(airport3);
        flight2.setDeparture(airport);
        flight2.setFirstOfficer(pilot3);
        flight2.setCaptain(pilot);

        flight2 = flightRepository.save(flight2);




    }



}
