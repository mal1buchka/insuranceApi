package com.kadyraliev.insuranceapi.rest.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseResponse {
    private int code;
    private String message;
}
