package com.project.app.main.controllers;

import com.project.app.main.dtos.ClientDto;
import com.project.app.main.entities.Client;
import com.project.app.main.services.ClientService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }




    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClient(@RequestParam Long id){
        return ResponseEntity.ok(service.deleteClient(id));
    }



    @GetMapping("/findById")
    public ResponseEntity<Client> findClientById(@RequestParam Long clientId){
        return ResponseEntity.ok(service.findClientById(clientId));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Client>> findAllClients(){
        return ResponseEntity.ok(service.getAllClients());
    }

}
