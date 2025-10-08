package com.project.app.main.repositories;

import com.project.app.main.entities.ChatOverview;
import com.project.app.main.entities.Client;
import com.project.app.main.entities.Owner;
import com.project.app.main.exceptions.NotFoundException;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class OwnerRepositoryTest {

    private final OwnerRepository repository;

    @Autowired
    OwnerRepositoryTest(OwnerRepository repository) {
        this.repository = repository;
    }

    Owner owner;
    Client client;

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
    }

    @Test
    void findById() {
        Client c = client;
        repository.save(c);
        assertNotNull(c.getId());
    }

    @Test
    void findByName() {
        Client c = client;
        repository.save(c);
        Owner foundOwner = repository.findByName("name")
                .orElseThrow(()-> new NotFoundException("Não foi possível encotrar um usuário com esse nome"));
        assertEquals(foundOwner.getName(), c.getName());
        assertEquals(foundOwner.getEmail(), c.getEmail());
        assertEquals(foundOwner.getPhone(), c.getPhone());
        assertEquals(foundOwner.getLocations(), c.getLocations());
        assertEquals(foundOwner.getChatOverviews(), c.getChatOverviews());

    }

    @Test
    void findByEmail() {

    }

    @Test
    void findByPhone() {
    }
}