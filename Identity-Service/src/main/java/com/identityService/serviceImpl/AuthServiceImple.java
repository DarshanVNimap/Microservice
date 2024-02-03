package com.identityService.serviceImpl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.identityService.UserRepository;
import com.identityService.config.CustomeUserDetail;
import com.identityService.dto.LoginRequest;
import com.identityService.dto.ResponseDto;
import com.identityService.dto.TokenResponse;
import com.identityService.dto.UserRequest;
import com.identityService.entity.UserEntity;
import com.identityService.service.AuthService;
import com.identityService.service.JwtService;

@Service
public class AuthServiceImple implements AuthService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	public ResponseDto registerUser(UserRequest request) {

		UserEntity user = mapper.map(request, UserEntity.class);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userRepo.save(user);
		return ResponseDto.builder().msg("Register successfull").status(HttpStatus.OK).build();
	}

	public TokenResponse loginUser(LoginRequest loginRequest) throws UserPrincipalNotFoundException {

		authManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		UserEntity getUser = userRepo.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new UserPrincipalNotFoundException("Invalid username or password!"));

		String getToken = jwtService.generateToken(new CustomeUserDetail(getUser));

		return TokenResponse.builder().accessToken(getToken).status(HttpStatus.OK).time(LocalDateTime.now()).build();
	}

}
