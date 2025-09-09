package com.project.app.main.dtos;

import com.project.app.main.enums.OverviewRating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatOverviewDto {
    private String overview;
    private OverviewRating rating;
    private String nextSugestedAction;
    private Long ownerId;
}
