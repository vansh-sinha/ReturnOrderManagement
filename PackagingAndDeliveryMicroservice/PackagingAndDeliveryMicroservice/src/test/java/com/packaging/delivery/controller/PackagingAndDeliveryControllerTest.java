package com.packaging.delivery.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PackagingAndDeliveryControllerTest {
    
	@Autowired
	PackagingAndDeliveryController padController;
	
	@Test
    @DisplayName("Checking if [PackagingAndDeliveryController] is loading or not.")
    void padControllerIsLoaded(){
        assertThat(padController).isNotNull();    
    }
	
	@Test
	 void testHealthCheck() {
		
		assertEquals("200 OK",padController.healthCheck().getStatusCode().toString());
		
	}
	
	
}
