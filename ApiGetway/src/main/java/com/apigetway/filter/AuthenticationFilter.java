package com.apigetway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

import org.springframework.http.HttpHeaders;

import jakarta.validation.Validator;

public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	public AuthenticationFilter() {
		super(Config.class);
		// TODO Auto-generated constructor stub
	}

	public static class Config{
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		// TODO Auto-generated method stub
		
		return ((exchange , chain) -> {
			
			if(Validator.)
			
			String getHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			
			
			
			return chain.filter(exchange);
		});
//		return null;
	}

}
