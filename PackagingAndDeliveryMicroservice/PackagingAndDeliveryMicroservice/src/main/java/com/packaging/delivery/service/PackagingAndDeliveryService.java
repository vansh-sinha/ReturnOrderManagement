package com.packaging.delivery.service;

import org.springframework.stereotype.Service;

import com.packaging.delivery.dto.PackagingAndDeliveryDTO;

@Service
public interface PackagingAndDeliveryService {
	
	public PackagingAndDeliveryDTO calculatePackagingAndDeliveryCharge(String Type, Integer count);

}
