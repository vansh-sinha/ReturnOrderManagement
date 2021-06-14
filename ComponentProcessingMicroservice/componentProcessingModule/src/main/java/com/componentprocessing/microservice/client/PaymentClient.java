package com.componentprocessing.microservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.componentprocessing.microservice.dto.PackagingAndDeliveryDTO;

import feign.Headers;

@Headers("Content-Type: application/json")
@FeignClient(name = "payment-service", url = "localhost:8001/payment")
public interface PaymentClient {

	@PostMapping("/processpayment/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}")
	PackagingAndDeliveryDTO paymentDetails( @PathVariable String requestId,@PathVariable int cardNumber,
			@PathVariable int creditLimit, @PathVariable int processingCharge,@RequestHeader(name = "Authorization", required = true) String token);

}
