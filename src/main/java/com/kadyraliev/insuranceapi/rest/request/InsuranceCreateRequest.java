package com.kadyraliev.insuranceapi.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kadyraliev.insuranceapi.enums.Type;
import com.kadyraliev.insuranceapi.rest.response.PassportKg;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(description = "Запрос на создание страхового полиса")
public class InsuranceCreateRequest {

    @Schema(
            description = "String Номер кредитного договора",
            example = "1234567890",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("contract_number")
    @NotEmpty(message = "contract number не должно быть пустым или null")
    @Pattern(regexp = "^//d+$", message = "contract number должен состоять только из цифр или набора цифр")
    private String contractNumber;

    @Schema(
            description = "Datetime Дата кредитного договора",
            example = "2025-03-26T10:44:34.356Z",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @JsonProperty("contract_date")
    private OffsetDateTime contractDate;

    @Schema(
            description = "Int Страховая премия",
            example = "1",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Positive(message = "страховая сумма должна быть всегда положительной")
    @JsonProperty("insurance_amount")
    private BigDecimal insuranceAmount;

    @Schema(
            description = "Enum (REFINANCING, DDU, FIRST_PAYMENT) Тип страховки",
            example = "REFINANCING",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Тип страховки обязателен")
    private Type type;

    @Schema(
            description = "Datetime Дата начала страховки (всегда будущая дата)",
            example = "2025-03-26T10:44:34.356Z",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Future(message = "Дата начала страховки (всегда будущая дата)")
    @JsonProperty("start_date_insurance")
    @NotNull(message = "Дата начала страховки обязательна")
    private OffsetDateTime startDateInsurance;

    @Schema(
            description = "Datetime Дата окончания страховки (всегда будущая дата)",
            example = "2025-03-26T10:44:34.356Z",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @Future(message = "Дата окончания страховки (всегда будущая дата)")
    @NotNull(message = "Дата окончания страховки обязательна")
    @JsonProperty("end_date_insurance")
    private OffsetDateTime endDateInsurance;

    @JsonProperty("passport_kg")
    private PassportKg passportKg;

}
