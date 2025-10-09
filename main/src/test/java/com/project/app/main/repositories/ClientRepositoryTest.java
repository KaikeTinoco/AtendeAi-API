package com.project.app.main.repositories;

import com.project.app.main.entities.Client;
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
class ClientRepositoryTest {
    private final ClientRepository repository;
    private final OwnerRepository ownerRepository;

    Client client;

    @Autowired
    ClientRepositoryTest(ClientRepository repository, OwnerRepository ownerRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
    }
    @BeforeEach
    void init() {
        client = Client.builder()
                .cpf("755.670.080-15")
                .email("email@gmail.com")
                .name("name")
                .chatOverviews(new ArrayList<>())
                .locations(new ArrayList<>())
                .phone("12345678900")
                .build();
    }

    @Test
    void findById() {
        Client c = client;
        ownerRepository.save(c);
        Client clientFound = repository.findById(1L)
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar um cliente de id 1"));
        assertEquals(clientFound, c);
    }

    @Test
    void findByCpf() {
        Client c = client;
        ownerRepository.save(c);
        Client clientFound = repository.findByCpf("755.670.080-15")
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar um cliente de id 1"));
        assertEquals(clientFound, c);
    }
}