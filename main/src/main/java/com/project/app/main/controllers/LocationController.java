package com.project.app.main.controllers;

import com.project.app.main.dtos.LocationDto;
import com.project.app.main.entities.Location;
import com.project.app.main.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/location")
public class LocationController {
    private final LocationService service;

    @Autowired
    public LocationController(LocationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<Location> registerLocation(@RequestBody LocationDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerLocation(dto));
    }

    @PutMapping("/update")
    public ResponseEntity<Location> updateLocation(@RequestBody LocationDto dto,
                                                   @RequestParam Long locationId){
        return ResponseEntity.ok(service.updateLocation(dto, locationId));
    }

    @PutMapping("/changeOwner")
    public ResponseEntity<Location> changeOwner(@RequestParam Long oldOwnerId,
                                                @RequestParam Long newOwnerId,
                                                @RequestParam Long locationId){
        return ResponseEntity.ok(service.changeOwner(oldOwnerId,newOwnerId, locationId));
    }
}
