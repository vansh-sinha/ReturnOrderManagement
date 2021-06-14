package com.payment.microservice.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "payment_details")
public class PaymentDetails {

	@Id
	@JsonProperty
	private String requestId;
	@JsonProperty
	private Integer cardNumber;
	@JsonProperty
	private int creditLimit;
	@JsonProperty
	private int processingCharge;
}
