package com.componentprocessing.microservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ReturnOrderResponse")

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProcessResponse {

	@Id
	@JsonProperty
	private String requestId;
	@JsonProperty
	private Integer processingCharge;
	@JsonProperty
	private Double packagingAndDeliveryCharge;
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String dateOfDelivery;
}
    