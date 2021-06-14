package com.payment.microservice.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.microservice.dto.PaymentDTO;
import com.payment.microservice.exception.InvalidFormatException;
import com.payment.microservice.model.PaymentDetails;
import com.payment.microservice.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{
	

	
	@Autowired
	private PaymentDetails paymentDetails;
	
	@Autowired
	private PaymentRepository repository;
	
    @Override
	public PaymentDTO processPaymentService(String RequestId,Integer cardNumber,Integer creditLimit,Integer processingCharge) throws InvalidFormatException{
		log.info("Payment service started");
		
			try {
				paymentDetails.setCardNumber(cardNumber);
				paymentDetails.setRequestId(RequestId);
				paymentDetails.setCreditLimit(creditLimit-processingCharge);
				paymentDetails.setProcessingCharge(processingCharge);
				repository.save(paymentDetails);
			} catch (Exception e) {
				throw new InvalidFormatException("Wrong type of the input provided...");
			}
			
		
		log.info("Payment service ended");
		return new PaymentDTO( (double)paymentDetails.getCreditLimit());
		
		
	}
    
	@Override
	public PaymentDetails reverseCredit(String requestId,Integer cardNumber) {
		Optional<PaymentDetails> optional= repository.findById(requestId);
		if(optional.isPresent()) {
			PaymentDetails details=optional.get();
			if(!optional.get().getCardNumber().equals(cardNumber)) {
				return null;
			}
			details.setCreditLimit(details.getCreditLimit()+details.getProcessingCharge());
			repository.save(details);
			return repository.findById(requestId).get();
		}
		return null;
	}

}
