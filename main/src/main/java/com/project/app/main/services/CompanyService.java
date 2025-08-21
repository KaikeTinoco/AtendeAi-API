package com.project.app.main.services;

import com.project.app.main.dtos.CompanyDto;
import com.project.app.main.entities.Company;
import com.project.app.main.exceptions.BadRequestException;
import com.project.app.main.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository repository;

    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }

    public Company registerCompany (CompanyDto dto){
        if(dto.getCnpj() == null || dto.getEmail() == null
                || dto.getName() == null || dto.getType() == null){
            throw new BadRequestException("por favor, preencha todos os dados da empresa!");
        }
        Company newCompany = Company.builder()
                .cnpj(dto.getCnpj())
                .name(dto.getName().replaceAll("\\s+", ""))
                .email(dto.getEmail())
                .type(dto.getType())
                .build();
        repository.save(newCompany);
        return newCompany;


    }
}
