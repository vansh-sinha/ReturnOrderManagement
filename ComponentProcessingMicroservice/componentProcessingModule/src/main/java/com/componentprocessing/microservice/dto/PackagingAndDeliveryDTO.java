package com.componentprocessing.microservice.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Component
public class PackagingAndDeliveryDTO {

	@JsonProperty
	private Double charge;

	@JsonIgnore
	public PackagingAndDeliveryDTO(Double charge) {
		this.charge = charge;
	}

	@JsonIgnore
	public Double getCharge() {
		return charge;
	}

	@JsonIgnore
	public void setCharge(Double charge) {
		this.charge = charge;
	}

}
