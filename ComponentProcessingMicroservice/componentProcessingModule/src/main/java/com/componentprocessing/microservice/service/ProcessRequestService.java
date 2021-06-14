package com.componentprocessing.microservice.service;

import org.springframework.stereotype.Service;

import com.componentprocessing.microservice.model.ProcessRequest;
import com.componentprocessing.microservice.model.ProcessResponse;

@Service
public interface ProcessRequestService {

    public ProcessResponse processService(ProcessRequest processRequestObj,String toekn);
    public String messageConfirmation(String requestId ,Integer creditCardNumber,
			 Integer creditLimit,  Integer processingCharge,String token);
    public String generateRequestId();
}
