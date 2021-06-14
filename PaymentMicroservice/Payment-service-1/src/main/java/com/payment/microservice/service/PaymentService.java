package com.payment.microservice.service;

import org.springframework.stereotype.Service;

import com.payment.microservice.dto.PaymentDTO;
import com.payment.microservice.model.PaymentDetails;


@Service
public interface PaymentService {

	public PaymentDTO processPaymentService(String RequestId,Integer cardNumber,Integer creditLimit,Integer processingCharge);
	public PaymentDetails reverseCredit(String requestId,Integer cardNumber);
}
