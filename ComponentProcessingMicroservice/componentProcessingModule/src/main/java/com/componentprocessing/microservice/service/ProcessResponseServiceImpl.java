package com.componentprocessing.microservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.componentprocessing.microservice.dto.PaymentDetailsDTO;
import com.componentprocessing.microservice.exceptions.ResponseNotFoundException;
import com.componentprocessing.microservice.model.ProcessResponse;
import com.componentprocessing.microservice.repository.ProcessResponseRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProcessResponseServiceImpl implements ProcessResponseService {

	@Autowired
	ProcessResponseRepo repo;

	private HttpEntity<String> getEntity(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(token.split(" ")[1]);

		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}

	@Override
	public String deleteResponse(String responseId, String cardNumber, String token) {
		Optional<ProcessResponse> optional = repo.findById(responseId);

		if (optional.isEmpty()) {
			return "Failed no such request id";
		}
		HttpEntity<String> entity = getEntity(token);
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8001/payment/reversePayment/{requestId}/{creditCardNumber}";
		ResponseEntity<PaymentDetailsDTO> dto = restTemplate.postForEntity(url, entity, PaymentDetailsDTO.class,
				responseId, cardNumber);
		if (dto.getBody() == null)
			return "Failed due to wrong credentials";
		repo.deleteById(responseId);
		return "Successfull, The updated credit limit is:" + dto.getBody().getCreditLimit();
	}

	@Override
	public ProcessResponse trackById(String responseId) {
		Optional<ProcessResponse> optional = repo.findById(responseId);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

}
