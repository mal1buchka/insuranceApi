package com.kadyraliev.insuranceapi.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kadyraliev.insuranceapi.enums.Gender;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

/*
    "name": "Бексултан1.", // цифры можно
    "surname": "Аалые12", // также цифры
    "patronymic": "Нурдинбекович",
    "gender": "M",
    "address": "<string>",
    "date_of_birth": "2025-03-26T10:44:34.356Z",
    "passport_authority": "МКК",
    "passport_authority_code": "23456789",
    "issued_date": "2020-03-26T00:00:00.921Z", // только со временем, нули
    "expired_date": "2030-03-26T00:00:00.921Z",
    "pin": "42345678900s",
    "passport_series": "<string>",
    "passport_number": "<string>"
 */


@Data
public class PassportKg {

//    private UUID id;

    private String name;

    private String surname;

    private String patronymic;

    private Gender gender;

    private String address;

    @JsonProperty("date_of_birth")
    @Past(message = "Дата рождения (Всегда дата в прошлом)")
    private OffsetDateTime dateOfBirth;

    @JsonProperty("passport_authority")
    private String passportAuthority;

    @JsonProperty("passport_authority_code")
    private String passportAuthorityCode;

    @JsonProperty("issued_date")
    @Past(message = "Дата выдачи паспорта")
    private OffsetDateTime issuedDate;

    @JsonProperty("expired_date")
    @Future(message = "Дата истечения срока действия паспорта (Всегда дата в будущем)")
    private OffsetDateTime expiredDate;

    private String pin;

    @JsonProperty("passport_series")
    private String passportSeries;

    @JsonProperty("passport_number")
    private String passportNumber;

//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @JsonProperty("created_at")
//    private LocalDateTime createdAt;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//    @JsonProperty("updated_at")
//    private LocalDateTime updateddAt;
}
