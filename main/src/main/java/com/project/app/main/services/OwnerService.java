package com.project.app.main.services;

import com.project.app.main.dtos.OwnerDto;
import com.project.app.main.entities.Client;
import com.project.app.main.entities.Company;
import com.project.app.main.entities.Location;
import com.project.app.main.entities.Owner;
import com.project.app.main.exceptions.BadRequestException;
import com.project.app.main.exceptions.NotFoundException;
import com.project.app.main.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    private final OwnerRepository repository;
    private final CompanyService companyService;
    private final ClientService clientService;

    @Autowired
    public OwnerService(OwnerRepository repository, CompanyService companyService, ClientService clientService) {
        this.repository = repository;
        this.companyService = companyService;
        this.clientService = clientService;
    }

    public Owner registerOwner(OwnerDto dto){
        if (dto.getName() == null || dto.getEmail() == null || dto.getPhone() == null) {
            throw new BadRequestException("Dados do proprietário incompletos.");
        }

        if (dto.getCpf() != null) {
            Client client = new Client();
            Client newClient = Client.builder()
                    .phone(dto.getPhone())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .locations(new ArrayList<>())
                    .cpf(dto.getCpf())
                    .build();
            repository.save(newClient);
            return newClient;

        } else if (dto.getCnpj() != null) {
            Company newCompany = Company.builder()
                    .phone(dto.getPhone())
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .locations(new ArrayList<>())
                    .cnpj(dto.getCnpj())
                    .build();
            repository.save(newCompany);
            return newCompany;

        } else {
            throw new BadRequestException("CPF ou CNPJ deve ser fornecido.");
        }
    }


    public Owner updateOwner(OwnerDto dto, Long ownerId){
        if(ownerId == null){
            throw new BadRequestException("Por favor informe um Id válido");
        }
        if(dto.getCpf() == null){
            Company company = companyService.findCompanyById(ownerId);
            Optional.ofNullable(dto.getName()).ifPresent(company::setName);
            Optional.ofNullable(dto.getEmail()).ifPresent(company::setEmail);
            Optional.ofNullable(dto.getCnpj()).ifPresent(company::setCnpj);
            Optional.ofNullable(dto.getPhone()).ifPresent(company::setPhone);
            repository.save(company);
            return company;
        } else {
            Client client = clientService.findClientById(ownerId);
            Optional.ofNullable(dto.getName()).ifPresent(client::setName);
            Optional.ofNullable(dto.getEmail()).ifPresent(client::setEmail);
            Optional.ofNullable(dto.getCpf()).ifPresent(client::setCpf);
            Optional.ofNullable(dto.getPhone()).ifPresent(client::setPhone);
            repository.save(client);
            return client;
        }

    }

    public String deleteOwner(Long ownerId) {
        if (ownerId == null) {
            throw new BadRequestException("Por favor informe um Id válido");
        }
        Owner owner = repository.findById(ownerId).orElseThrow(
                () -> new NotFoundException("Não foi possível encontrar um dono com id:" + ownerId)
        );
        repository.delete(owner);
        return "Dono deletado com sucesso!";
    }

    public List<Owner> findAll(){
        return repository.findAll();
    }
    public Owner findOwnerById(Long ownerId){
        if (ownerId == null) {
            throw new BadRequestException("Por favor informe um Id válido");
        }
        Owner owner = repository.findById(ownerId).orElseThrow(
                () -> new NotFoundException("Não foi possível encontrar um dono com id:" + ownerId)
        );
        return owner;
    }
}
