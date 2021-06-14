package com.authorizationservice.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.authorizationservice.authorization.model.AuthenticationRequest;

public interface AuthRequestRepo extends JpaRepository<AuthenticationRequest, String> {

}
