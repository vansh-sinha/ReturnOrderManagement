package com.payment.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.microservice.model.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, String>{

}
