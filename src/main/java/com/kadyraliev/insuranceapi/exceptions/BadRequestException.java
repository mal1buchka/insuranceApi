package com.kadyraliev.insuranceapi.exceptions;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    int code;

    public BadRequestException(int code, String message) {
        super(message);
        this.code = code;
    }
}
