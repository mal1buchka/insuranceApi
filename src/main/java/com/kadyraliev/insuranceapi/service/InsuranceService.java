package com.kadyraliev.insuranceapi.service;

import com.kadyraliev.insuranceapi.clients.InsuranceClient;
import com.kadyraliev.insuranceapi.enums.Type;
import com.kadyraliev.insuranceapi.model.Insurance;
import com.kadyraliev.insuranceapi.repository.InsuranceRepository;
import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.response.InsuranceCreateResponse;
import com.kadyraliev.insuranceapi.rest.response.InsurancePatchResponse;
import com.kadyraliev.insuranceapi.rest.response.InsuranceGetResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.kadyraliev.insuranceapi.controller.advice.util.FeignHandler.handleFeignException;


@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceClient insuranceClient;
    private final InsuranceRepository insuranceRepository;


    public InsuranceGetResponse getInsurance(String policyUuid) {
        return insuranceClient.getInsurance(policyUuid);
    }

    public List<InsuranceGetResponse> listInsurance(Integer take,
                                                    Integer skip,
                                                    Type insuranceType,
                                                    String order,
                                                    String orderType,
                                                    LocalDateTime dateFrom,
                                                    LocalDateTime dateTo
    ) {
        return insuranceClient.listInsurance(take, skip, insuranceType, order, orderType, dateFrom, dateTo);
    }

    public InsuranceCreateResponse createInsurance(InsuranceCreateRequest request) {
        try {
            InsuranceCreateResponse response = insuranceClient.createInsurance(request);
            Insurance insurance = Insurance.fromRequest(response);
            insuranceRepository.save(insurance);
            return response;
        }catch (FeignException e){
            throw handleFeignException(e, null);
        }
    }

    public InsurancePatchResponse patchInsurance(String id, InsurancePatchRequest request) {
        return insuranceClient.patchInsurance(id, request);
    }

    public String viewInsuranceFile(UUID id) {
        Insurance insurance = insuranceRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Could not find insurance with id " + id)
        );
        return insurance.getContentAsBase64();
    }
}
