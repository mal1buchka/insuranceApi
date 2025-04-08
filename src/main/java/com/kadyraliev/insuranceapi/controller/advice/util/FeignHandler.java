package com.kadyraliev.insuranceapi.controller.advice.util;

import com.kadyraliev.insuranceapi.exceptions.BadRequestException;
import com.kadyraliev.insuranceapi.exceptions.PolicyNotFoundException;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.kadyraliev.insuranceapi.enums.StatusCodes.NOT_FOUND;
import static com.kadyraliev.insuranceapi.enums.StatusCodes.NOT_VALID_INPUT_DATA;

@Slf4j
@Component
public class FeignHandler {

    public static RuntimeException handleFeignException(FeignException ex, String policyId){

        switch (ex.status()) {
            case 422 -> throw new BadRequestException(NOT_VALID_INPUT_DATA.getStatus(),
                    NOT_VALID_INPUT_DATA.getMessage());

            case 404 -> throw new PolicyNotFoundException(NOT_FOUND.getStatus(),
                    String.format(NOT_FOUND.getMessage(), policyId ));

            default -> throw new RuntimeException("Неожиданный FeignException: " + ex.getMessage());
        }

    }
}
