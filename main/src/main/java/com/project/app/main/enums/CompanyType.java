package com.project.app.main.enums;

import lombok.Getter;

@Getter
public enum CompanyType {
    BRANCH("branch"),
    HEADQUEARTERS("hq");

    private final String type;

    CompanyType(String type) {
        this.type = type;
    }
}
