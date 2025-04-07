package com.kadyraliev.insuranceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodes {
    SUCCESS(0, "Успех"),
    UNKNOWN_ERROR(-1, "Неопознанная ошибка"),
    NOT_VALID_INPUT_DATA(2, "Неправильно введены данные для запроса");


    private final Integer status;
    private final String message;
}
