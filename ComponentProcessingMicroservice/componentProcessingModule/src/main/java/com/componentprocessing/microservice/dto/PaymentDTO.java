package com.componentprocessing.microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentDTO {

	@JsonProperty
	private Double pay;

	
	public PaymentDTO(Double pay) {
		this.pay = pay;
	}


	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

}
