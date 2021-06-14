package com.componentprocessing.microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PaymentDetailsDTO {
	@JsonProperty
	private String requestId;
	@JsonProperty
	private Integer cardNumber;
	@JsonProperty
	private int creditLimit;
	@JsonProperty
	private int processingCharge;
}
