package com.project.app.main.enums;

import lombok.Getter;

@Getter
public enum OwnerType {
    COMPANY("company"),
    PERSON("person");

    private final String type;

    OwnerType(String type) {
        this.type = type;
    }
}
