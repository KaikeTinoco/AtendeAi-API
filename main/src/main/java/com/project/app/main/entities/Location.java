package com.project.app.main.entities;

import com.project.app.main.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client owner;

    private String street;
    private int houseNumber;
    private String neighborhood;
    private String city;
    private String complement;
    private State state;

}
