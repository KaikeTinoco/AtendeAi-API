package com.project.app.main.controllers;

import com.project.app.main.dtos.ClientDto;
import com.project.app.main.entities.Client;
import com.project.app.main.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody ClientDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerClient(dto));
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteClient(@RequestParam Long id){
        return ResponseEntity.ok(service.deleteClient(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Client> updateClient(@RequestBody ClientDto dto,
                                               @RequestParam Long clientId){
        return ResponseEntity.ok(service.updateClient(clientId, dto));
    }

}
