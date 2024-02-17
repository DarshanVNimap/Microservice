package com.identityService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.identityService.entity.UserEntity;
import com.identityService.repository.UserRepository;


public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity getUser = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
		return new CustomeUserDetail(getUser);
	}}
