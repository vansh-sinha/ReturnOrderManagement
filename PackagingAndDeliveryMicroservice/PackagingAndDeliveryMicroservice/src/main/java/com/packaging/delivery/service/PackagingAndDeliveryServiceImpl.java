package com.packaging.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packaging.delivery.dto.PackagingAndDeliveryDTO;
import com.packaging.delivery.exceptions.ComponentTyepNotFoundException;
import com.packaging.delivery.model.PackagingAndDelivery;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PackagingAndDeliveryServiceImpl implements PackagingAndDeliveryService {



	@Autowired
	PackagingAndDelivery packagingAndDelivery;

	int totalCharge = 0;

	@Override
	public PackagingAndDeliveryDTO calculatePackagingAndDeliveryCharge(String type, Integer count) {
		log.info("=========Begin======calculatePackagingAndDeliveryCharge()");
		if (("Integral").equalsIgnoreCase(type)) {
			log.info("Integral");
			packagingAndDelivery.setPackagingCost(100);
			packagingAndDelivery.setDeliveryCost(200);

		} else if (("Accessory").equalsIgnoreCase(type)) {
			log.info("Accessory");
			packagingAndDelivery.setPackagingCost(50);
			packagingAndDelivery.setDeliveryCost(100);
		} else if (("Protective sheath").equalsIgnoreCase(type)) {
			packagingAndDelivery.setPackagingCost(50);
		} else {
			throw new ComponentTyepNotFoundException("Component Type: " + type + "not found.");
		}

		totalCharge = (packagingAndDelivery.getPackagingCost() + packagingAndDelivery.getDeliveryCost()) * count;
		log.debug("" + totalCharge);
		log.info("=========End======calculatePackagingAndDeliveryCharge()");

		return new PackagingAndDeliveryDTO(totalCharge);

	}

}
