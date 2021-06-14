package com.returnorder.portal.client;

import com.returnorder.portal.dto.PaymentStatusDTO;
import com.returnorder.portal.dto.ProcessDTO;
import com.returnorder.portal.model.ProcessRequest;


import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "component-client", url = "localhost:8080/component/componentprocessingmodule")
public interface ComponentClient {
    @PostMapping("/ProcessDetail")
    ProcessDTO processResponseDetails(@RequestBody ProcessRequest processRequestObj ,@RequestHeader(name = "Authorization", required = true) String token);

    @PostMapping("/completeProcessing/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}")
	PaymentStatusDTO statusConfirmation(@PathVariable String requestId, @PathVariable Integer cardNumber,
			@PathVariable Integer creditLimit, @PathVariable Integer processingCharge ,@RequestHeader(name = "Authorization", required = true) String token);

    
}
    