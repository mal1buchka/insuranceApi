package com.kadyraliev.insuranceapi.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ErrorDetail {
    @JsonProperty("loc")
    private List<String> loc;

    @JsonProperty("msg")
    private String msg;

    @JsonProperty("type")
    private String type;
}
