package com.identityService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomeExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ResponseEntity<?> exception(Exception e){
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
