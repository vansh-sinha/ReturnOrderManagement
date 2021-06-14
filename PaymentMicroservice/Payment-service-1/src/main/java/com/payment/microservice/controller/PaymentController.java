package com.payment.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.payment.microservice.dto.PaymentDTO;
import com.payment.microservice.exception.InvalidTokenException;
import com.payment.microservice.feign.AuthClient;
import com.payment.microservice.model.PaymentDetails;
import com.payment.microservice.service.PaymentService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PaymentController {

	@Autowired
	private PaymentService paymentServiceImpl;
	@Autowired
	private AuthClient authClient;

	@PostMapping(path = "/processpayment/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentDTO> paymentDetails(@PathVariable String requestId, @PathVariable int cardNumber,
			@PathVariable int creditLimit, @PathVariable int processingCharge,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		try {
			if (!authClient.getsValidity(token).isValidStatus()) {

				throw new InvalidTokenException("Token is either expired or invalid...");
			}

		} catch (FeignException e) {
			throw new InvalidTokenException("Token is either expired or invalid...");

		}

		log.info("Controller");
		try {
			return new ResponseEntity<>(
					paymentServiceImpl.processPaymentService(requestId, cardNumber, creditLimit, processingCharge),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(
					paymentServiceImpl.processPaymentService(requestId, cardNumber, creditLimit, processingCharge),
					HttpStatus.FORBIDDEN);

		}

	}

	@PostMapping(path = "/reversePayment/{requestId}/{cardNumber}")
	public ResponseEntity<PaymentDetails> reversePayment(@PathVariable int cardNumber, @PathVariable String requestId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		try {
			if (!authClient.getsValidity(token).isValidStatus()) {
				throw new InvalidTokenException("Token is either expired or invalid...");
			}

		} catch (FeignException e) {
			throw new InvalidTokenException("Token is either expired or invalid...");

		}

		return new ResponseEntity<PaymentDetails>(paymentServiceImpl.reverseCredit(requestId, cardNumber),
				HttpStatus.OK);
	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		log.info("Payment Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
