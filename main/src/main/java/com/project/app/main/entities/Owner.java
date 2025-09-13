package com.project.app.main.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Entity
@Table(name = "owners")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "owner_type", discriminatorType = DiscriminatorType.STRING)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations;


    private String name;

    @Email
    private String email;

    private String phone;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatOverview> chatOverviews;

    public void addLocation(Location location){
        this.locations.add(location);
    }

    public void removeLocation(Location location){
        this.locations.remove(location);
    }

    public void addChatOverview(ChatOverview chatOverview){
        this.chatOverviews.add(chatOverview);
    }

}
