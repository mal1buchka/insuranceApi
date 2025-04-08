package com.kadyraliev.insuranceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCodes {
    SUCCESS(0, "Успех"),
    UNKNOWN_ERROR(-1, "Поставщик недоступен"),
    NOT_VALID_INPUT_DATA(2, "Неправильно введены данные для запроса"),
    FEIGN_ERROR(3, "Ошибка с внешним сервисом"),
    NOT_FOUND(4, "Полис по такому номеру %s не найден"),
    INVALID_TOKEN(5, "Неправильный или недействительный токен");

    private final Integer status;
    private final String message;
}
