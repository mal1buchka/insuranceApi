package com.kadyraliev.insuranceapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kadyraliev.insuranceapi.enums.Status;
import com.kadyraliev.insuranceapi.enums.Type;
import com.kadyraliev.insuranceapi.rest.request.InsuranceCreateRequest;
import com.kadyraliev.insuranceapi.rest.response.InsuranceCreateResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.UUID;

@Table(name = "insurances")
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    public UUID id;

    @Column(nullable = false, name = "policy_uuid", unique = true)
    private UUID policyUuid;

    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    @Column(nullable = false, name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @JsonProperty("policy_number")
    @Column(nullable = false, name = "policy_number")
    private Integer policyNumber;

    @JsonProperty("insurance_amount")
    @Column(nullable = false, name = "insurance_amount")
    private BigDecimal insuranceAmount;

    @JsonProperty("insurance_premium")
    @Column(nullable = false, name = "insurance_premium")
    private Integer insurancePremium;

    @Column(nullable = false, name = "status")
    private Status status;

    @Column(nullable = false, name = "type")
    private Type type;

    @Lob
    @Column(nullable = false, name = "file")
    private byte[] file;

    @Lob
    @Column(name = "scanned_file")
    private byte[] scannedFile;

    public void setContentFromBase64(String base64) {
        this.file = Base64.getDecoder().decode(base64);
    }

    public String getContentAsBase64() {
        return Base64.getEncoder().encodeToString(file);
    }

    public static Insurance fromRequest(InsuranceCreateResponse request) {
        return Insurance.builder()
                .policyUuid(request.getId())
                .policyNumber(request.getPolicyNumber())
                .insuranceAmount(request.getInsuranceAmount())
                .insurancePremium(request.getInsurancePremium())
                .status(request.getStatus())
                .type(request.getType())
                .build();
    }
}
