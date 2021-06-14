package com.componentprocessing.microservice.service;

import org.springframework.stereotype.Service;

import com.componentprocessing.microservice.model.ProcessResponse;

@Service
public interface ProcessResponseService {
	public String deleteResponse(String responseId,String cardNumber,String token);
	public ProcessResponse trackById(String responseId);
}
