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
                //removes the blank spaces between the name
                .nome(dto.getNome().replaceAll("\\s+", ""))
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .locations(new ArrayList<>())
                .build();
        repository.save(newCLient);
        return newCLient;
    }

    public Client updateClient(Long clientId, ClientDto update){
        Client client = repository.findById(clientId).get();
        if (!update.getCpf().isEmpty()){
            client.setCpf(update.getCpf());
        }
        if (update.getNome().isEmpty()){
            client.setNome(update.getNome());
        }
        if(!update.getEmail().isEmpty()){
            client.setEmail(update.getEmail());
        }
        repository.save(client);
        return client;
    }

    public String deleteClient(Long clientId){
        Client client = repository.findById(clientId).get();
        repository.delete(client);
        return "client deletado com sucesso!";
    }
}
