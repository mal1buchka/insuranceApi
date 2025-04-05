package com.kadyraliev.insuranceapi.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsurancePatchRequest {

    @NotEmpty
    @Pattern(regexp = "PAID")
    private String status;

    @NotEmpty
    @JsonProperty("scan_file")
    private String scanFile;

}
