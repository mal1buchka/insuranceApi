package com.kadyraliev.insuranceapi.service;

import com.kadyraliev.insuranceapi.clients.InsuranceClient;
import com.kadyraliev.insuranceapi.model.Insurance;
import com.kadyraliev.insuranceapi.repository.InsuranceRepository;
import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.response.InsurancePatchResponse;
import com.kadyraliev.insuranceapi.rest.response.InsuranceGetResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
                                                    String insuranceType,
                                                    String order,
                                                    String orderType,
                                                    LocalDateTime dateFrom,
                                                    LocalDateTime dateTo
    ) {
        return insuranceClient.listInsurance(take, skip, insuranceType, order, orderType, dateFrom, dateTo);
    }

    public InsuranceGetResponse createInsurance(InsuranceCreateRequest request) {
        InsuranceGetResponse response = insuranceClient.createInsurance(request);
        Insurance insurance = new Insurance();
        insurance.setContentFromBase64(response.getFile());
        insuranceRepository.save(insurance);
        return response;
    }

    public InsurancePatchResponse patchInsurance(String id, InsurancePatchRequest request) {
        return insuranceClient.patchInsurance(id, request);
    }
}
