package com.payment.microservice.dto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;
@NoArgsConstructor
public class PaymentDTO {
	
	@JsonProperty
	private Double charge;
		@JsonIgnore
		public  PaymentDTO(Double charge) {
			this.charge=charge;
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
