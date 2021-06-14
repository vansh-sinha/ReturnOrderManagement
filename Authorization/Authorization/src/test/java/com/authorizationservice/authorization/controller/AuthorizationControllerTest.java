package com.authorizationservice.authorization.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.authorizationservice.authorization.exceptions.LoginException;
import com.authorizationservice.authorization.model.AuthenticationRequest;
import com.authorizationservice.authorization.repository.AuthRequestRepo;
import com.authorizationservice.authorization.service.AppUserDetailsService;
import com.authorizationservice.authorization.util.JwtUtil;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class AuthorizationControllerTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private AppUserDetailsService appUserDetailsService;

	@Mock
	private AuthRequestRepo authRequestRepo;

	@InjectMocks
	private AuthorizationController authenticationController;

	@Test
	void testValidLogin() throws LoginException {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequest.getUserName(), authenticationRequest.getPassword(),
				new ArrayList<>());

		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);
		when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-token");

		ResponseEntity<Object> authenticationResponse = authenticationController
				.createAuthorizationToken(authenticationRequest);
		assertEquals(HttpStatus.OK, authenticationResponse.getStatusCode());
	}


	@Test
	void testInvalidLogin() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("adyasha1", "abhishek2");
		UserDetails userDetails = new User(authenticationRequest.getUserName(), "adyasha1", new ArrayList<>());
		
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);
		Exception exception = Assertions.assertThrows(LoginException.class,
				() -> authenticationController.createAuthorizationToken(authenticationRequest));
		assertEquals("Invalid Username or Password", exception.getMessage());
	}

	@Test
	void testValidToken() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequest.getUserName(), authenticationRequest.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(true);
		when(jwtUtil.extractUsername("token")).thenReturn("adyasha1");
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertTrue(validity.getBody().toString().contains("true"));
	}

	@Test
	void testInvalidToken() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequest.getUserName(), authenticationRequest.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(false);
		when(jwtUtil.extractUsername("token")).thenReturn("adyasha1");
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertEquals(true, validity.getBody().toString().contains("false"));
	}

	@Test
	void testInvalidTokenWhenSignatureInvalid() {
		AuthenticationRequest authenticationRequest = new AuthenticationRequest("adyasha1", "adyasha1");
		UserDetails userDetails = new User(authenticationRequest.getUserName(), authenticationRequest.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenThrow(SignatureException.class);
		when(jwtUtil.extractUsername("token")).thenReturn("adyasha1");
		when(appUserDetailsService.loadUserByUsername("adyasha1")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertEquals(true, validity.getBody().toString().contains("false"));
	}
}
