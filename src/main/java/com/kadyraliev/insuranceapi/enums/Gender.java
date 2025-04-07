package com.kadyraliev.insuranceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    M("Male(Мужчина)"),
    F("Female(Женщина)");

    private final String description;
}
