package com.project.app.main.repositories;

import com.project.app.main.entities.Client;
import com.project.app.main.entities.Company;
import com.project.app.main.enums.CompanyType;
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
class CompanyRepositoryTest {
    private final CompanyRepository repository;
    private final OwnerRepository ownerRepository;

    @Autowired
    CompanyRepositoryTest(CompanyRepository repository, OwnerRepository ownerRepository) {
        this.repository = repository;
        this.ownerRepository = ownerRepository;
    }

    Company company;
    @BeforeEach
    void init() {
        company = Company.builder()
                .cnpj("93.054.822/0001-36")
                .email("email@gmail.com")
                .name("name")
                .chatOverviews(new ArrayList<>())
                .locations(new ArrayList<>())
                .phone("12345678900")
                .type(CompanyType.HEADQUEARTERS)
                .build();
    }


    @Test
    void findById() {
        Company c = company;
        ownerRepository.save(c);
        Company companyFound = repository.findById(1L)
                .orElseThrow(()-> new NotFoundException("não foi possível achar uma empresa com id 1"));
        assertEquals(c, companyFound);
    }

    @Test
    void findByCnpj() {
        Company c = company;
        ownerRepository.save(c);
        Company companyFound = repository.findByCnpj("93.054.822/0001-36")
                .orElseThrow(()->
                        new NotFoundException("não foi possível achar uma empresa com cnpj: 93.054.822/0001-36"));
        assertEquals(c, companyFound);
    }
}