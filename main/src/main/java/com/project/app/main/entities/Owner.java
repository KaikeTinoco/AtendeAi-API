package com.project.app.main.entities;

import jakarta.persistence.*;

import java.util.List;

@MappedSuperclass
public abstract class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations;
}
