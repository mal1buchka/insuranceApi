package com.kadyraliev.insuranceapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadyraliev.insuranceapi.clients.InsuranceClient;
import com.kadyraliev.insuranceapi.enums.Type;
import com.kadyraliev.insuranceapi.model.Insurance;
import com.kadyraliev.insuranceapi.repository.InsuranceRepository;
import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.response.ErrorResponse;
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


@Slf4j
@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceClient insuranceClient;
    private final InsuranceRepository insuranceRepository;
    private final ObjectMapper objectMapper;

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
        InsuranceCreateResponse response = insuranceClient.createInsurance(request);

        // Обработка бизнес-логики, если response вернулся успешно
        // Например, проверка, что статус = SUCCESS (если ты его прокидываешь)
        // или просто работа с пришедшими полями
        Insurance insurance = Insurance.fromRequest(response);
        insuranceRepository.save(insurance);

        return response;
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
