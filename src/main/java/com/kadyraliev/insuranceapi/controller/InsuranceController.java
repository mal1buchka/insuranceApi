package com.kadyraliev.insuranceapi.controller;

import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.request.InsurancePatchRequest;
import com.kadyraliev.insuranceapi.rest.response.InsurancePatchResponse;
import com.kadyraliev.insuranceapi.rest.response.InsuranceGetResponse;
import com.kadyraliev.insuranceapi.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/insurance")
@RequiredArgsConstructor
@Tag(name = "Страховые полиса", description = "API для работы со страховыми полисами")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @Operation(
            summary = "Получить страховой полис по UUID",
            description = "Возвращает полную информацию о страховом полисе по его уникальному идентификатору",
            parameters = {
                    @Parameter(
                            name = "policyUuid",
                            description = "UUID страхового полиса",
                            required = true,
                            in = ParameterIn.PATH,
                            schema = @Schema(type = "string", example = "550e8400-e29b-41d4-a716-446655440000")
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Страховой полис найден",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    schema = @Schema(implementation = InsuranceGetResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "404",
            description = "Страховой полис не найден"
    )
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{policyUuid}")
    public InsuranceGetResponse getInsurance(
            @PathVariable String policyUuid) {
        return insuranceService.getInsurance(policyUuid);
    }

    @Operation(
            summary = "Получить список страховых полисов",
            description = "Возвращает список страховых полисов с возможностью фильтрации, сортировки и пагинации",
            parameters = {
                    @Parameter(
                            name = "take",
                            description = "получить определенное кол-во записей – по умолчанию 10",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "10")
                    ),
                    @Parameter(
                            name = "skip",
                            description = "пропустить определенное кол-во записей (по умолчанию 0)",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "integer", defaultValue = "0")
                    ),
                    @Parameter(
                            name = "insurance_type",
                            description = "фильтр по типам страховых полисов \n Enum (REFINANCING, DDU, FIRST_PAYMENT",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string")
                    ),
                    @Parameter(
                            name = "order",
                            description = "выбор сортировки по (created_at, updated_at, policy_number)",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", allowableValues = {"created_at", "updated_at", "policy_number"})
                    ),
                    @Parameter(
                            name = "order_type",
                            description = "сортировка по возрастанию или убыванию (ASC/DESC)",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", allowableValues = {"ASC", "DESC"})
                    ),
                    @Parameter(
                            name = "date_from",
                            description = "фильтрация даты от",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "date-time")
                    ),
                    @Parameter(
                            name = "date_to",
                            description = "фильтрация даты до",
                            in = ParameterIn.QUERY,
                            schema = @Schema(type = "string", format = "date-time")
                    )
            }
    )
    @ApiResponse(
            responseCode = "200",
            description = "Список страховых полисов",
            content = @io.swagger.v3.oas.annotations.media.Content(
                    schema = @Schema(implementation = InsuranceGetResponse.class, type = "array")
            )
    )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InsuranceGetResponse> listInsurance(
            @RequestParam(name = "take", defaultValue = "10", required = false)
            Integer take,
            @RequestParam(name = "skip", defaultValue = "0", required = false)
            Integer skip,
            @RequestParam(name = "insurance_type", required = false)
            String insuranceType,
            @RequestParam(name = "order", required = false)
            @Pattern(regexp = "created_at|updated_at|policy_number", message = "Invalid order value")
            String order,
            @RequestParam(name = "order_type", required = false)
            @Pattern(regexp = "ASC|DESC", message = "Invalid order type")
            String orderType,
            @RequestParam(name = "date_from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dateFrom,
            @RequestParam(name = "date_to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dateTo
    ) {
        return insuranceService.listInsurance(take, skip, insuranceType, order, orderType, dateFrom, dateTo);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(
            summary = "Создание нового страхового полиса",
            description = "Принимает данные полиса и сохраняет их в БД."
    )
    @ApiResponse(
            responseCode = "201",
            description = "Полис успешно создан"
    )
    @ApiResponse(
            responseCode = "400",
            description = "Невалидные данные (например, отрицательная страховая сумма)"
    )
    public InsuranceGetResponse createInsurance(@RequestBody @Valid InsuranceCreateRequest request) {
        return insuranceService.createInsurance(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{policyUuid}")
    public InsurancePatchResponse patchInsurance(@PathVariable String policyUuid, @RequestBody @Valid InsurancePatchRequest request) {
        return insuranceService.patchInsurance(policyUuid, request);
    }


}
