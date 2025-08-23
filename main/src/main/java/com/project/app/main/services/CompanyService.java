package com.project.app.main.services;

import com.project.app.main.dtos.CompanyDto;
import com.project.app.main.entities.Company;
import com.project.app.main.exceptions.BadRequestException;
import com.project.app.main.exceptions.NotFoundException;
import com.project.app.main.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Company updateCompany(Long companyId, CompanyDto dto) {
        if (companyId == null) {
            throw new BadRequestException("por favor informe o id da empresa a ser atualizada!");
        }
        Company company = repository.findById(companyId).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma empresa com id: " + companyId)
        );
        Optional.ofNullable(dto.getCnpj()).ifPresent(company::setCnpj);
        Optional.ofNullable(dto.getName()).ifPresent(company::setName);
        Optional.ofNullable(dto.getEmail()).ifPresent(company::setEmail);
        Optional.ofNullable(dto.getTelefone()).ifPresent(company::setTelefone);
        repository.save(company);
        return company;
    }

    public String deleteCompany(Long companyId){
        if (companyId == null) {
            throw new BadRequestException("por favor informe o id da empresa a ser atualizada!");
        }
        Company company = repository.findById(companyId).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma empresa com id: " + companyId)
        );
        repository.delete(company);
        return "deletado com sucesso!";
    }


}
