package com.identityService.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.identityService.dto.LoginRequest;
import com.identityService.dto.UserRequest;
import com.identityService.service.AuthService;


@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
		
		System.out.println(userRequest.getFirstName());
		
		return ResponseEntity.ok(authService.registerUser(userRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		try {
			return ResponseEntity.ok(authService.loginUser(loginRequest));
		} catch (UserPrincipalNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
