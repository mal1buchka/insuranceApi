package com.kadyraliev.insuranceapi.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Lob
    @Column(name = "file", columnDefinition = "bytea")
    private byte[] file;

    public void setContentFromBase64(String base64) {
        this.file = Base64.getDecoder().decode(base64);
    }

    public String getContentAsBase64() {
        return Base64.getEncoder().encodeToString(file);
    }
}
