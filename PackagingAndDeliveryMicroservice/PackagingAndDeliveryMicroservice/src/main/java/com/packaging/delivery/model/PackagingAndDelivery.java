package com.packaging.delivery.model;



import org.springframework.stereotype.Component;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Component
public class PackagingAndDelivery {
	

	private int id;
	private int packagingCost;
	private int deliveryCost;

}
