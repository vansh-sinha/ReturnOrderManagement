package com.componentprocessing.microservice.dto;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PaymentStatusDTO {
    
    @Id
    @JsonProperty
    private String status;
}