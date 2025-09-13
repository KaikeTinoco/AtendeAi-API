package com.project.app.main.repositories;

import com.project.app.main.entities.ChatOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<ChatOverview, UUID> {
    Optional<ChatOverview> findById(UUID id);

}
