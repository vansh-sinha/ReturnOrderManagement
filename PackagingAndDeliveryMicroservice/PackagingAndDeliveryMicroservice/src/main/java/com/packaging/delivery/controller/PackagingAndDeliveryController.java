package com.packaging.delivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.packaging.delivery.dto.PackagingAndDeliveryDTO;
import com.packaging.delivery.exceptions.InvalidTokenException;
import com.packaging.delivery.feign.AuthClient;
import com.packaging.delivery.service.PackagingAndDeliveryService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PackagingAndDeliveryController {

	@Autowired
	private PackagingAndDeliveryService packagingAndDeliveryService;
	@Autowired
	private AuthClient authClient;

	@PostMapping(path = "/getPackagingDeliveryCharge/{type}/{count}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PackagingAndDeliveryDTO> calculatePackagingAndDeliveryCharge(@PathVariable String type,
			@PathVariable Integer count, @RequestHeader(name = "Authorization", required = true) String token)
			throws InvalidTokenException {
		try {
			if (!authClient.getsValidity(token).isValidStatus()) {

				throw new InvalidTokenException("Token is either expired or invalid...");
			}

		} catch (FeignException e) {
			throw new InvalidTokenException("Token is either expired or invalid...");

		}

		try {
			return new ResponseEntity<>(packagingAndDeliveryService.calculatePackagingAndDeliveryCharge(type, count),
					HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(packagingAndDeliveryService.calculatePackagingAndDeliveryCharge(type, count),
					HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(path = "/health-check")
	public ResponseEntity<String> healthCheck() {
		log.info("PackagingAndDelivery Microservice is Up and Running....");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}
