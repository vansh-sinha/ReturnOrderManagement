package com.componentprocessing.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componentprocessing.microservice.model.ProcessResponse;

@Repository
public interface ProcessResponseRepo extends JpaRepository<ProcessResponse, String> {


}