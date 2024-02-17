package com.identityService.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.identityService.dto.LoginRequest;
import com.identityService.dto.ResponseDto;
import com.identityService.dto.UserRequest;
import com.identityService.service.AuthService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserRequest userRequest){
		
		return ResponseEntity.ok(authService.registerUser(userRequest));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
		try {
			return ResponseEntity.ok(authService.loginUser(loginRequest));
		} catch (UserPrincipalNotFoundException e) {
			return ResponseEntity.badRequest().body(ResponseDto.builder().msg(e.getMessage()).status(HttpStatus.BAD_REQUEST).build());
		}
	}
	
	@GetMapping("/validate")
	public ResponseEntity<?> validateToken(@RequestParam(name = "token") String token){
		return ResponseEntity.ok(authService.validateToken(token)); 
	}

}
