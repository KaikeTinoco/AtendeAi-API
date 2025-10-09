package com.project.app.main.repositories;

import com.project.app.main.entities.Client;
import com.project.app.main.entities.Location;
import com.project.app.main.entities.Owner;
import com.project.app.main.enums.State;
import com.project.app.main.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class LocationRepositoryTest {
    private final LocationRepository repository;
    private final OwnerRepository ownerRepository;

    Owner owner;
    Client client;
    Location location;

    @Autowired
    LocationRepositoryTest(LocationRepository repository, OwnerRepository ownerRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
    }

    @BeforeEach
    void init(){
        client = Client.builder()
                .cpf("755.670.080-15")
                .email("email@gmail.com")
                .name("name")
                .chatOverviews(new ArrayList<>())
                .locations(new ArrayList<>())
                .phone("12345678900")
                .build();

        location = Location.builder()
                .city("city")
                .street("street")
                .owner(client)
                .state(State.MATO_GROSSO_DO_SUL)
                .neighborhood("neighborhood")
                .houseNumber(123)
                .complement("complement")
                .build();

    }


    @Test
    void findById() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findById(3L)
                .orElseThrow(() -> new NotFoundException("não foi possível encontrar uma localização com id 1"));
        assertEquals(l, locationFound);
    }

    @Test
    void findByStreet() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findByStreet("street")
                .orElseThrow(() -> new NotFoundException("não foi possível encontrar uma localização com id 1"));
        assertEquals(l, locationFound);
    }

    @Test
    void findByHouseNumber() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findByHouseNumber(123)
                .orElseThrow(() -> new NotFoundException("não foi possível encontrar uma localização com essa rua"));
        assertEquals(l, locationFound);
    }

    @Test
    void findByNeighborhood() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findByNeighborhood("neighborhood")
                .orElseThrow(() -> new NotFoundException("não foi possível encontrar uma localização com esse bairro"));
        assertEquals(l, locationFound);
    }

    @Test
    void findByCity() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findByCity("city")
                .orElseThrow(() -> new NotFoundException("não foi possível encontrar uma localização com essa cidade"));
        assertEquals(l, locationFound);
    }

    @Test
    void findByComplement() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findByComplement("complement")
                .orElseThrow(() -> new
                        NotFoundException("não foi possível encontrar uma localização com essa complemento"));
        assertEquals(l, locationFound);
    }

    @Test
    void findByState() {
        Client c = client;
        ownerRepository.save(c);
        Location l = location;
        repository.save(l);
        Location locationFound = repository.findByState(State.MATO_GROSSO_DO_SUL)
                .orElseThrow(() -> new NotFoundException("não foi possível encontrar uma localização com esse estado"));
        assertEquals(l, locationFound);
    }
}