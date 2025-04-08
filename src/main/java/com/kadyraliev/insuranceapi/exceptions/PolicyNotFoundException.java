package com.kadyraliev.insuranceapi.exceptions;

public class PolicyNotFoundException extends RuntimeException {

    private int code;

    public PolicyNotFoundException(int code, String message) {
        super(message);
        this.code = code;
    }
}
