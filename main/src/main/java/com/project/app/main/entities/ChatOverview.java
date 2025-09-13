package com.project.app.main.entities;

import com.project.app.main.enums.OverviewRating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "chats")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatOverview {
    @Id
    @GeneratedValue
    private UUID id;

    private String overview;
    private OverviewRating rating;
    private String nextSugestedAction;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
