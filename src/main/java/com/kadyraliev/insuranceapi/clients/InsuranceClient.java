package com.kadyraliev.insuranceapi.clients;


import com.kadyraliev.insuranceapi.enums.Type;
import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.response.InsuranceCreateResponse;
import com.kadyraliev.insuranceapi.rest.response.InsurancePatchResponse;
import com.kadyraliev.insuranceapi.rest.response.InsuranceGetResponse;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{policyUuid}")
    InsuranceGetResponse getInsurance(
            @PathVariable("policyUuid") String policyUuid);

    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    List<InsuranceGetResponse> listInsurance(

            @RequestParam(name = "take", defaultValue = "10", required = false) Integer take,

            @RequestParam(name = "skip", defaultValue = "0", required = false) Integer skip,

            @RequestParam(name = "insurance_type", required = false)
            @Pattern(regexp = "^(REFINANCING|DDU|FIRST_PAYMENT)$",
                    message = "Поле может быть REFINANCING или DDU или FIRST_PAYMENT") Type insuranceType,

            @RequestParam(name = "order", required = false)
            @Pattern(regexp = "^(created_at|updated_at|policy_number)$",
                    message = "Поле order может быть только create_at или updated_at или policy_number") String order,

            @RequestParam(name = "order_type", required = false)
            @Pattern(regexp = "^(ASC|DESC)$",
                    message = "Поле order_type может быть только ASC или DESC") String orderType,

            @RequestParam(name = "date_from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateFrom,

            @RequestParam(name = "date_to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTo
    );

    @PostMapping()
    InsuranceCreateResponse createInsurance(
            @RequestBody InsuranceCreateRequest insuranceCreateRequest);

    @RequestMapping(value = "/{policyUuid}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    InsurancePatchResponse patchInsurance(@PathVariable("policyUuid") String policyUuid,
                                          @RequestBody InsurancePatchRequest request);
}
