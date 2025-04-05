package com.kadyraliev.insuranceapi.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kadyraliev.insuranceapi.enums.Status;
import com.kadyraliev.insuranceapi.enums.Type;
import jakarta.validation.constraints.Future;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class InsuranceGetResponse {

    private UUID id;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    private LocalDateTime updatedAt;

    @JsonProperty("policy_number")
    private Integer policyNumber;

    @JsonProperty("contract_number")
    private String contractNumber;

    @JsonProperty("contract_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime contractDate;

    @JsonProperty("insurance_amount")
    private Integer insuranceAmount;

    @JsonProperty("insurance_premium")
    private Integer insurancePremium;

    @JsonProperty("start_date_insurance")
    @Future(message = "Дата начала страховки (всегда будущая дата)")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime startDateInsurance;

    @JsonProperty("end_date_insurance")
    @Future(message = "Дата окончания страховки (всегда будущая дата)")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime endDateInsurance;

    private Status status;

    private Type type;

    private String file;

//    @JsonProperty("scan_file")
//    private String scanFile;
//
//    @JsonProperty("passport_kg")
//    private PassportKg passportKG;
}

