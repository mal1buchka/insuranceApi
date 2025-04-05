package com.kadyraliev.insuranceapi.controller;

import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.response.InsurancePatchResponse;
import com.kadyraliev.insuranceapi.rest.response.InsuranceGetResponse;
import com.kadyraliev.insuranceapi.service.InsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{policyUuid}")
    public InsuranceGetResponse getInsurance(
            @PathVariable String policyUuid){
        return insuranceService.getInsurance(policyUuid);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceGetResponse> listInsurance(
            @RequestParam(name = "take", defaultValue = "10", required = false)
            Integer take,
            @RequestParam(name = "skip", defaultValue = "0", required = false)
            Integer skip,
////            @RequestParam(name = "insurance_type", required = false)
            String insuranceType,
////            @RequestParam(name = "order", required = false)
////            @Pattern(regexp = "created_at|updated_at|policy_number", message = "Invalid order value")
            String order,
////            @RequestParam(name = "order_type", required = false)
////            @Pattern(regexp = "ASC|DESC", message = "Invalid order type")
            String orderType,
////            @RequestParam(name = "date_from", required = false)
////            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dateFrom,
////            @RequestParam(name = "date_to", required = false)
////            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dateTo
    ){
        return insuranceService.listInsurance(take, skip, insuranceType, order, orderType, dateFrom, dateTo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public InsuranceGetResponse createInsurance(@RequestBody InsuranceCreateRequest request){
        return insuranceService.createInsurance(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{policyUuid}")
    public InsurancePatchResponse patchInsurance(@PathVariable String policyUuid, @RequestBody InsurancePatchRequest request) {
        return insuranceService.patchInsurance(policyUuid, request);
    }


}
