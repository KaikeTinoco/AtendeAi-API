package com.project.app.main.controllers;

import com.project.app.main.dtos.OwnerDto;
import com.project.app.main.entities.Owner;
import com.project.app.main.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/owner")
public class OwnerController {
    private final OwnerService service;

    @Autowired
    public OwnerController(OwnerService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Owner> registerOwner(@RequestBody OwnerDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerOwner(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Owner> updateOwner(@RequestBody OwnerDto dto,
                                             @RequestParam Long ownerId){
        return ResponseEntity.ok(service.updateOwner(dto, ownerId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOwner(@RequestParam Long ownerId){
        return ResponseEntity.ok(service.deleteOwner(ownerId));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Owner>> getAllOwners(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/findById")
    public ResponseEntity<Owner> findOwnerById(@RequestParam Long ownerId){
        return ResponseEntity.ok(service.findOwnerById(ownerId));
    }

}
