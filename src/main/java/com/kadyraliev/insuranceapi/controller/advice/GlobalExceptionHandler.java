package com.kadyraliev.insuranceapi.controller.advice;

import com.kadyraliev.insuranceapi.enums.StatusCodes;
import com.kadyraliev.insuranceapi.exceptions.BadRequestException;
import com.kadyraliev.insuranceapi.rest.response.BaseResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.InternalServerError.class)
    public ResponseEntity<BaseResponse> handleFeignInternalServerException(FeignException ex) {

        log.error("Feign exception: Internal Server Error -> {}", ex.getMessage(), ex);

        BaseResponse baseResponse = BaseResponse.builder()
                .code(StatusCodes.UNKNOWN_ERROR.getStatus())
                .message(StatusCodes.UNKNOWN_ERROR.getMessage())
                .build();

        return ResponseEntity
                .status(ex.status())
                .body(baseResponse);
    }

    @ExceptionHandler(FeignException.Forbidden.class)
    public ResponseEntity<BaseResponse> handleForbiddenException(FeignException.Forbidden ex) {
        log.error("Feign exception: Forbidden -> {}", ex.getMessage());

        BaseResponse baseResponse = BaseResponse.builder()
                .code(StatusCodes.INVALID_TOKEN.getStatus())
                .message(StatusCodes.INVALID_TOKEN.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(baseResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BaseResponse> handleBadRequestException(BadRequestException ex) {
        log.error("Bad request exception -> {}", ex.getMessage());

        BaseResponse baseResponse = BaseResponse.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(baseResponse);
    }


}
