package com.project.app.main.dtos;

import com.project.app.main.enums.State;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {
    private Long ownerDocument;
    private String street;
    private int houseNumber;
    private String neighborhood;
    private String city;
    private String complement;
    private State state;
}
