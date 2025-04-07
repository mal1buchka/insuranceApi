package com.kadyraliev.insuranceapi.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import feign.FeignException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ErrorResponse {
    @JsonProperty("detail")
    private List<ErrorDetail> detail = new ArrayList<>();

//    public static ErrorResponse fromFeignException(FeignException.FeignClientException ex) {
//        try {
//            String responseBody = ex.responseBody().map;
//
//        } catch (Exception e) {
//            return new ErrorResponse();
//        }
//    }
}
