package com.project.app.main.services;

import com.project.app.main.dtos.CompanyDto;
import com.project.app.main.entities.Company;
import com.project.app.main.exceptions.BadRequestException;
import com.project.app.main.exceptions.NotFoundException;
import com.project.app.main.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository repository;

    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.repository = repository;
    }


    public String deleteCompany(Long companyId){
        if (companyId == null) {
            throw new BadRequestException("por favor informe o id da empresa a ser deletada!");
        }
        Company company = repository.findById(companyId).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma empresa com id: " + companyId)
        );
        repository.delete(company);
        return "deletado com sucesso!";
    }

    public Company findCompanyById(Long companyId){
        if (companyId == null) {
            throw new BadRequestException("por favor informe o id da empresa");
        }
        Company company = repository.findById(companyId).orElseThrow(
                () -> new NotFoundException("não foi possível encontrar uma empresa com id: " + companyId)
        );
        return company;
    }

    public List<Company> findAllCompanies (){
        return repository.findAll();
    }


}
