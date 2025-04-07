package com.kadyraliev.insuranceapi.rest.response;

import com.kadyraliev.insuranceapi.enums.StatusCodes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private StatusCodes statusCode;
    private Integer code;
    private String message;
    private ErrorResponse error;

    public void setStatusCode(StatusCodes statusCode) {
        this.statusCode = statusCode;
        this.message = statusCode.getMessage();
        this.code = statusCode.getStatus();
    }
}
