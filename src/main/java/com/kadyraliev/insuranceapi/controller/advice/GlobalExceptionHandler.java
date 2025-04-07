package com.kadyraliev.insuranceapi.controller.advice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadyraliev.insuranceapi.enums.StatusCodes;
import com.kadyraliev.insuranceapi.rest.response.BaseResponse;
import com.kadyraliev.insuranceapi.rest.response.ErrorDetail;
import com.kadyraliev.insuranceapi.rest.response.ErrorResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<BaseResponse> handleFeignException(FeignException ex) {
        log.error("Feign exception -> {}", ex.contentUTF8(), ex);
        String content = ex.contentUTF8();

        try {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(StatusCodes.NOT_VALID_INPUT_DATA);

            JsonNode root = objectMapper.readTree(content);
            JsonNode detailNode = root.get("detail");

            if (detailNode != null) {
                ErrorResponse errorResponse = new ErrorResponse();

                if (detailNode.isArray()) {
                    errorResponse = objectMapper.treeToValue(root, ErrorResponse.class);
                } else {
                    ErrorDetail detail = ErrorDetail.builder()
                            .msg(detailNode.asText())
                            .build();

                    errorResponse.setDetail(List.of(detail));
                }

                baseResponse.setError(errorResponse);
            } else {
                // нет detail — можно создать стандартную ошибку
                ErrorDetail detail = ErrorDetail.builder()
                        .type("missing_detail")
                        .msg("Поле detail отсутствует в ответе")
                        .loc(List.of())
                        .build();

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setDetail(List.of(detail));
                baseResponse.setError(errorResponse);
            }

            return ResponseEntity
                    .status(ex.status())
                    .body(baseResponse);

        } catch (Exception e) {
            log.error("Ошибка при разборе FeignException", e);

            BaseResponse fallbackResponse = new BaseResponse();
            fallbackResponse.setStatusCode(StatusCodes.UNKNOWN_ERROR);
            fallbackResponse.setMessage("Ошибка при разборе ошибки внешнего сервиса");

            return ResponseEntity
                    .status(HttpStatus.BAD_GATEWAY)
                    .body(fallbackResponse);
        }
    }


}
