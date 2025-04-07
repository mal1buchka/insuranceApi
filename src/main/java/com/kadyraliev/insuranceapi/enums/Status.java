package com.kadyraliev.insuranceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Status {
    CREATED("Страховой полис создан"),
    PAID("Страховой полис оплачен");

    private final String description;
}
