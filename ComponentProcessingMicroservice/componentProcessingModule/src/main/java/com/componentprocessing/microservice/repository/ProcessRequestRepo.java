package com.componentprocessing.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.componentprocessing.microservice.model.ProcessRequest;

@Repository
public interface ProcessRequestRepo extends JpaRepository<ProcessRequest, String> {


}
