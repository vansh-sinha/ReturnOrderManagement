package com.packaging.delivery.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatingDTOTest {
	
	ValidatingDTO validatingDTO= new ValidatingDTO();
	
	@Test
    @DisplayName("Checking if [ValidatingDTO] is loading or not.")
    void packagingAndDeliveryDTOIsLoaded(){
        assertThat(validatingDTO).isNotNull();    
    }
	
	@Test
	 void testPackagingAndDelivery() {
		validatingDTO = new ValidatingDTO(true);
	
		validatingDTO.setValidStatus(true);
		assertEquals(true,validatingDTO.isValidStatus());
		assertFalse(validatingDTO.toString().isEmpty());
		
	}


}
