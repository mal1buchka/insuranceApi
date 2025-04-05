package com.kadyraliev.insuranceapi.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kadyraliev.insuranceapi.enums.Type;
import com.kadyraliev.insuranceapi.rest.response.PassportKg;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class InsuranceCreateRequest {

    @JsonProperty("contract_number")
    @Pattern(regexp = "^//d$")
    private String contractNumber;

    @JsonProperty("contract_date")
    private OffsetDateTime contractDate;

    @JsonProperty("insurance_amount")
    private BigDecimal insuranceAmount;

    private Type type;

    @JsonProperty("start_date_insurance")
    private OffsetDateTime startDateInsurance;

    @JsonProperty("end_date_insurance")
    private OffsetDateTime endDateInsurance;

    @JsonProperty("passport_kg")
    private PassportKg passportKg;

}
