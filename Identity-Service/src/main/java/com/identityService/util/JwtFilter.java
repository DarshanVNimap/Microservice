package com.identityService.util;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.identityService.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	private final HandlerExceptionResolver exceptionResolver;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
		final String header = request.getHeader("Authoriation");
		
		if(header == null || !header.startsWith("Bearer")) {
			doFilter(request, response, filterChain);
			return ;
		}
		
		final String getToken = header.substring(7);
		final String username = jwtService.extarctUsername(getToken);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetail = userDetailsService.loadUserByUsername(username);
			
			if(jwtService.isValidToken(getToken, userDetail)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username , null, userDetail.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			exceptionResolver.resolveException(request, response, null, e);
		}
		
		
	}

}
