package com.project.app.main.services;

import com.project.app.main.dtos.ClientDto;
import com.project.app.main.entities.Client;
import com.project.app.main.exceptions.BadRequestException;
import com.project.app.main.exceptions.NotFoundException;
import com.project.app.main.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ClientService {
    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client registerClient(ClientDto dto){
        if(dto.getCpf() == null || dto.getEmail() == null || dto.getNome() == null){
            throw new BadRequestException("por favor, preencha todos os dados do cliente!");
        }
        Client newCLient = Client.builder()
                //removes the blank spaces between the name
                .nome(dto.getNome().replaceAll("\\s+", ""))
                .cpf(dto.getCpf())
                .email(dto.getEmail())
                .build();
        repository.save(newCLient);
        return newCLient;
    }

    public Client updateClient(Long clientId, ClientDto update){
        if(clientId == null){
            throw new BadRequestException("por favor, informe um Id válido");
        }
        Client client = repository.findById(clientId).
                orElseThrow( () -> new NotFoundException("não foi possível encontrar um cliente com esse id"));
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
        if(clientId == null){
            throw new BadRequestException("por favor, informe um Id válido");
        }
        Client client = repository.findById(clientId).
                orElseThrow( () -> new NotFoundException("não foi possível encontrar um cliente com esse id"));
        repository.delete(client);
        return "client deletado com sucesso!";
    }

    public Client findClientById(Long clientId){
        if(clientId == null){
            throw new BadRequestException("por favor, informe um Id válido");
        }
        return repository.findById(clientId).
                orElseThrow( () -> new NotFoundException("não foi possível encontrar um cliente com esse id"));
    }

    public List<Client> getAllClients(){
        return repository.findAll();
    }

}
