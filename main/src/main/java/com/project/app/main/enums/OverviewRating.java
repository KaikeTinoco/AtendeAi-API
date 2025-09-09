package com.project.app.main.enums;

public enum OverviewRating {
    POSITIVE("positive"),
    NEUTRAL("neutral"),
    NEGATIVE("negative");

    private final String name;

    OverviewRating(String name) {
        this.name = name;
    }
}
