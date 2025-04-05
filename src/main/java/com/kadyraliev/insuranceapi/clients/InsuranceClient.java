package com.kadyraliev.insuranceapi.clients;


import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.response.InsurancePatchResponse;
import com.kadyraliev.insuranceapi.rest.response.InsuranceGetResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(
        name = "${spring.application.name}",
        url = "${base.url}",
        path = "/insurance"
)
public interface InsuranceClient {

    @GetMapping("/{policyUuid}")
    InsuranceGetResponse getInsurance(
            @PathVariable("policyUuid") String policyUuid);

    @GetMapping()
    List<InsuranceGetResponse> listInsurance(
            @RequestParam(name = "take", defaultValue = "10", required = false) Integer take,
            @RequestParam(name = "skip", defaultValue = "0", required = false) Integer skip,
            @RequestParam(name = "insurance_type", required = false) String insuranceType,
            @RequestParam(name = "order", required = false) String order,
            @RequestParam(name = "order_type", required = false) String orderType,
            @RequestParam(name = "date_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,
            @RequestParam(name = "date_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo
    );

    @PostMapping()
    InsuranceGetResponse createInsurance(
            @RequestBody @Valid InsuranceCreateRequest insuranceCreateRequest);

    @RequestMapping(value = "/{policyUuid}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    InsurancePatchResponse patchInsurance(@PathVariable("policyUuid") String policyUuid, @RequestBody InsurancePatchRequest request);
}
