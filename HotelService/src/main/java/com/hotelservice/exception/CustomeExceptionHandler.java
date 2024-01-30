package com.hotelservice.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hotelservice.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomeExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().stream().map(e -> errors.put(e.getField(), e.getDefaultMessage()))
				.toList();

		return ResponseEntity.badRequest().body(errors);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> getException(Exception e) {
		log.error(e.getMessage());
		return ResponseEntity.badRequest().body(ResponseDto.builder().message(e.getMessage())
				.status(HttpStatus.BAD_REQUEST).timestamp(new Date()).build()

		);
	}
}
