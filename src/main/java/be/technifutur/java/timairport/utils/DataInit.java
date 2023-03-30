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

    // ------------------------------------------------------------------------------------- COMPANY

        Company company = new Company();
        company.setName("AirMan");
        company.setOriginCountry("USA");

        company = companyRepository.save(company);

        Company company2 = new Company();
        company2.setName("DreamSky");
        company2.setOriginCountry("Belgium");

        company2 = companyRepository.save(company2);

        Company company3 = new Company();
        company3.setName("GoSun");
        company3.setOriginCountry("Spain");

        company3 = companyRepository.save(company3);

// ----------------------------------------------------------------- AIRPORT

        Airport airport = new Airport();
        airport.setName("Pompano");
        airport.setCountry("USA");
        airport.setCity("Fort Lauderdale");
        airport.setPlaneParking(27);
        airport.setPlaneTypesAllowed(types3);

        airport = airportRepository.save(airport);

        Airport airport2 = new Airport();
        airport2.setName("Vancity");
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

        Airport airport4 = new Airport();
        airport4.setName("IbiLand");
        airport4.setCountry("Espagne");
        airport4.setCity("Ibiza");
        airport4.setPlaneParking(18);
        airport4.setPlaneTypesAllowed(types);

        airport4 = airportRepository.save(airport4);

        Airport airport5 = new Airport();
        airport5.setName("LalaLand");
        airport5.setCountry("UK");
        airport5.setCity("London");
        airport5.setPlaneParking(12);
        airport5.setPlaneTypesAllowed(types3);

        airport5 = airportRepository.save(airport5);

        Airport airport6 = new Airport();
        airport6.setName("AmstFly");
        airport6.setCountry("Pays-Bas");
        airport6.setCity("Amsterdam");
        airport6.setPlaneParking(32);
        airport6.setPlaneTypesAllowed(types);

        airport6 = airportRepository.save(airport6);

        Airport airport7 = new Airport();
        airport7.setName("AirBrux");
        airport7.setCountry("Belgique");
        airport7.setCity("Bruxelles");
        airport7.setPlaneParking(40);
        airport7.setPlaneTypesAllowed(types3);

        airport7 = airportRepository.save(airport7);

// ------------------------------------------------------------------------------------- PILOT

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

        Pilot pilot4 = new Pilot();
        pilot4.setLicenseId("FCL0589PL87");
        pilot4.setCompany(company3);
        pilot4.setLicenseAcquisition(LocalDate.from(LocalDateTime.of(2021,5,15,19,0)));
        pilot4.setAddress("Blv de la régence 78, Holland");
        pilot4.setEmail("myservices@hotmail.com");
        pilot4.setFirstName("Vincent");
        pilot4.setLastName("Leonard");
        pilot4.setPhone("+4985765245");

        pilot4 = pilotRepository.save(pilot4);

    // ------------------------------------------------------------------------------------- PLANE

        Plane plane = new Plane();
        plane.setCallSign("F-GUVM");
        plane.setCompany(company);
        plane.setRegistrationDate(LocalDate.of(2008,9,7));
        plane.setInMaintenance(false);
        plane.setType(type2);

        plane = planeRepository.save(plane);

        Plane plane2 = new Plane();
        plane2.setCallSign("F-HABM");
        plane2.setCompany(company2);
        plane2.setRegistrationDate(LocalDate.of(2014,5,10));
        plane2.setInMaintenance(false);
        plane2.setType(type);

        plane2 = planeRepository.save(plane2);

        Plane plane3 = new Plane();
        plane3.setCallSign("F-GXNC");
        plane3.setCompany(company3);
        plane3.setRegistrationDate(LocalDate.of(2010,7,4));
        plane3.setInMaintenance(false);
        plane3.setType(type3);

        plane3 = planeRepository.save(plane3);

        Plane plane4 = new Plane();
        plane4.setCallSign("N-121RO");
        plane4.setCompany(company3);
        plane4.setRegistrationDate(LocalDate.of(2018,2,1));
        plane4.setInMaintenance(false);
        plane4.setType(type2);

        plane4 = planeRepository.save(plane4);

        Plane plane5 = new Plane();
        plane5.setCallSign("OO-1FFT");
        plane5.setCompany(company2);
        plane5.setRegistrationDate(LocalDate.of(2009,4,14));
        plane5.setInMaintenance(false);
        plane5.setType(type3);

        plane5 = planeRepository.save(plane5);

        Plane plane6 = new Plane();
        plane6.setCallSign("OO-YLOP");
        plane6.setCompany(company);
        plane6.setRegistrationDate(LocalDate.of(2012,10,21));
        plane6.setInMaintenance(false);
        plane6.setType(type);

        plane6 = planeRepository.save(plane6);

    // ------------------------------------------------------------------------------------- FLIGHT

        Flight flight = new Flight();
        flight.setId(1);
        flight.setArrivalTime(LocalDateTime.of(2023, 4, 28, 12, 00));
        flight.setDepartureTime(LocalDateTime.of(2023, 4, 28, 06, 00));
        flight.setPlane(plane);
        flight.setDestination(airport);
        flight.setDeparture(airport2);
        flight.setFirstOfficer(pilot4);
        flight.setCaptain(pilot2);
        flight.setCompany(company);

        flight = flightRepository.save(flight);

        Flight flight2 = new Flight();
        flight2.setId(2);
        flight2.setArrivalTime(LocalDateTime.of(2023, 5, 13, 5, 30));
        flight2.setDepartureTime(LocalDateTime.of(2023, 5, 12, 1, 0));
        flight2.setPlane(plane2);
        flight2.setDestination(airport3);
        flight2.setDeparture(airport);
        flight2.setFirstOfficer(pilot3);
        flight2.setCaptain(pilot);
        flight.setCompany(company2);

        flight2 = flightRepository.save(flight2);

        Flight flight3 = new Flight();
        flight3.setId(3);
        flight3.setArrivalTime(LocalDateTime.of(2023, 6, 27, 21, 0));
        flight3.setDepartureTime(LocalDateTime.of(2023, 6, 28, 4, 0));
        flight3.setPlane(plane3);
        flight3.setDestination(airport5);
        flight3.setDeparture(airport7);
        flight3.setFirstOfficer(pilot2);
        flight3.setCaptain(pilot3);
        flight.setCompany(company3);

        flight3 = flightRepository.save(flight3);

        Flight flight4 = new Flight();
        flight4.setId(4);
        flight4.setArrivalTime(LocalDateTime.of(2023, 8, 15, 8, 0));
        flight4.setDepartureTime(LocalDateTime.of(2023, 8, 15, 15, 0));
        flight4.setPlane(plane5);
        flight4.setDestination(airport6);
        flight4.setDeparture(airport4);
        flight4.setFirstOfficer(pilot4);
        flight4.setCaptain(pilot);
        flight.setCompany(company2);

        flight4 = flightRepository.save(flight4);

        Flight flight5 = new Flight();
        flight5.setId(5);
        flight5.setArrivalTime(LocalDateTime.of(2023, 12, 9, 13, 0));
        flight5.setDepartureTime(LocalDateTime.of(2023, 12, 9, 22, 0));
        flight5.setPlane(plane6);
        flight5.setDestination(airport3);
        flight5.setDeparture(airport2);
        flight5.setFirstOfficer(pilot3);
        flight5.setCaptain(pilot2);
        flight.setCompany(company);

        flight5 = flightRepository.save(flight5);




    }



}
