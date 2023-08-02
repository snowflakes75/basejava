package com.urise.webapp.model;

public enum ContactType {
    TELEPHONE("Тел."),
    SKYPE("Skype"),
    EMAIl("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String name;

    ContactType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
