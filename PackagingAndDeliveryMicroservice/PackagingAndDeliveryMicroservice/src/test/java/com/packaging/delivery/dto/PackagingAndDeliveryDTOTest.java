package com.packaging.delivery.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PackagingAndDeliveryDTOTest {
	
	PackagingAndDeliveryDTO packagingAndDeliveryDTO= new PackagingAndDeliveryDTO();
	
	@Test
    @DisplayName("Checking if [PackagingAndDeliveryDTO] is loading or not.")
    public void packagingAndDeliveryDTOIsLoaded(){
        assertThat(packagingAndDeliveryDTO).isNotNull();    
    }

	@Test
	public void testPackagingAndDelivery() {
		packagingAndDeliveryDTO = new PackagingAndDeliveryDTO(50.0);
		packagingAndDeliveryDTO.setCharge(20.0);
		assertEquals(20.0,packagingAndDeliveryDTO.getCharge());
		
	}
	
	
}
