package com.kadyraliev.insuranceapi.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kadyraliev.insuranceapi.enums.Status;
import com.kadyraliev.insuranceapi.enums.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(description = "Ответ на создание страхового полиса")
@Getter
@Setter
public class InsuranceCreateResponse {

    private UUID id;

    @JsonProperty("created_at")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime updatedAt;

    @JsonProperty("policy_number")
    private Integer policyNumber;

    @JsonProperty("insurance_amount")
    private BigDecimal insuranceAmount;

    @JsonProperty("insurance_premium")
    private Integer insurancePremium;

    private Status status;

    private Type type;

    private String file;
}
