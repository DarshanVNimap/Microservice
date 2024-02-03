package com.identityService.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.identityService.UserRepository;
import com.identityService.entity.UserEntity;

public class CustomeUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userRepo.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Invalid username"));

		return new CustomeUserDetail(user , authorites(user.getId()));
	}
	
	public List<GrantedAuthority> authorites(String userId){
		
		return null;
		
	}

}
