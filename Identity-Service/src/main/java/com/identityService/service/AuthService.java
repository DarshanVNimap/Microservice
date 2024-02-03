package com.identityService.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import com.identityService.dto.LoginRequest;
import com.identityService.dto.ResponseDto;
import com.identityService.dto.TokenResponse;
import com.identityService.dto.UserRequest;

public interface AuthService {
	
	public ResponseDto registerUser(UserRequest request);
	public TokenResponse loginUser(LoginRequest loginRequest) throws UserPrincipalNotFoundException;

}
