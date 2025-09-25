package com.project.app.main.services;

import com.project.app.main.dtos.LocationDto;
import com.project.app.main.entities.Client;
import com.project.app.main.entities.Company;
import com.project.app.main.entities.Location;
import com.project.app.main.entities.Owner;
import com.project.app.main.exceptions.NotFoundException;
import com.project.app.main.repositories.LocationRepository;
import com.project.app.main.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class LocationService {
    private final LocationRepository repository;
    private final CompanyService companyService;
    private final ClientService clientService;
    private final OwnerRepository ownerRepository;

    @Autowired
    public LocationService(LocationRepository repository, CompanyService companyService, ClientService clientService, OwnerRepository ownerRepository) {
        this.repository = repository;

        this.companyService = companyService;
        this.clientService = clientService;
        this.ownerRepository = ownerRepository;
    }


    public Location registerLocation(LocationDto dto){
        Owner owner = ownerRepository.findById(dto.getOwnerId())
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar um proprietário com id:"
                        + dto.getOwnerId()));

        Location newLocation = Location.builder()
                .city(dto.getCity().replaceAll(" ", "_"))
                .state(dto.getState())
                .complement(dto.getComplement().replaceAll(" ", "_"))
                .houseNumber(dto.getHouseNumber())
                .street(dto.getStreet().replaceAll(" ", "_"))
                .neighborhood(dto.getNeighborhood().replaceAll(" ", "_"))
                .owner(owner)
                .build();


        repository.save(newLocation);
        owner.addLocation(newLocation);
        ownerRepository.save(owner);
        return newLocation;
    }

    public Location updateLocation(LocationDto dto, Long locationId){
        Location location = repository.findById(locationId)
                .orElseThrow(()->
                        new NotFoundException("Não foi possível encontrar um localização com id:" + locationId));

        Optional.ofNullable(dto.getCity()).ifPresent(location::setCity);
        Optional.ofNullable(dto.getNeighborhood()).ifPresent(location::setNeighborhood);
        Optional.ofNullable(dto.getState()).ifPresent(location::setState);
        Optional.ofNullable(dto.getComplement()).ifPresent(location::setComplement);
        Optional.ofNullable(dto.getHouseNumber()).ifPresent(location::setHouseNumber);
        Optional.ofNullable(dto.getStreet()).ifPresent(location::setStreet);
        repository.save(location);
        return location;
    }

    public Location changeOwner(Long oldOwnerId, Long newOwnerId, Long locationId){
        Owner oldOwner = ownerRepository.findById(oldOwnerId)
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar um proprietário com id:"
                        + oldOwnerId));
        Owner newOwner = ownerRepository.findById(newOwnerId)
                .orElseThrow(()-> new NotFoundException("Não foi possível encontrar um proprietário com id:"
                        + newOwnerId));

        Location location = repository.findById(locationId)
                .orElseThrow(()->
                        new NotFoundException("Não foi possível encontrar uma localização com id:" + locationId));

        location.setOwner(newOwner);
        repository.save(location);
        oldOwner.removeLocation(location);
        newOwner.addLocation(location);
        ownerRepository.saveAll(Arrays.asList(oldOwner, newOwner));
        return location;
    }


}
