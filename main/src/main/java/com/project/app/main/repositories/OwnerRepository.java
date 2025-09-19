package com.project.app.main.repositories;

import com.project.app.main.entities.Client;
import com.project.app.main.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findById(Long id);
    Optional<Owner> findByName(String nome);
    Optional<Owner> findByEmail(String email);
    Optional<Owner> findByPhone(String phone);
}
