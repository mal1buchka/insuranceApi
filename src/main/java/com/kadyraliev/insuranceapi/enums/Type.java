package com.kadyraliev.insuranceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Type {
    REFINANCING("Рефинансирование"),
    DDU("Договор долевого участия"),
    FIRST_PAYMENT("Первый платеж");

    private final String description;
}
