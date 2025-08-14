package com.project.app.main.services;

import com.project.app.main.dtos.ClientDto;
import com.project.app.main.entities.Client;
import com.project.app.main.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client registerClient(ClientDto dto){
        Client newCLient = Client.builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .locations(new ArrayList<>())
                .build();
        repository.save(newCLient);
        return newCLient;
    }
}
