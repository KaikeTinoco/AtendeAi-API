package com.project.app.main.repositories;

import com.project.app.main.entities.Location;
import com.project.app.main.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findById(Long locationId);
    Optional<Location> findByStreet(String street);
    Optional<Location> findByHouseNumber(int houseNumber);
    Optional<Location> findByNeighborhood(String neighborhood);
    Optional<Location> findByCity(String city);
    Optional<Location> findByComplement(String complement);
    Optional<Location> findByState(State state);
}
