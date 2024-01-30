package com.hotelservice.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelservice.dto.HotelDto;
import com.hotelservice.dto.ResponseDto;
import com.hotelservice.service.HotelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hotel")
public class HotelServiceController {
	
	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllHotelDetail(){
		return ResponseEntity.ok(hotelService.getAllHotel());
	}
	
	@GetMapping("/{hotelId}")
	public ResponseEntity<?> getHotelDetailById(@PathVariable Long hotelId){
		return ResponseEntity.ok(hotelService.getHotel(hotelId));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> addHotelDetails(@RequestBody @Valid HotelDto hotelDto){
		try {
			return ResponseEntity.ok(hotelService.addHotel(hotelDto));
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().body(ResponseDto.builder()
				          .message(e.getMessage())
				          .status(HttpStatus.BAD_REQUEST)
				          .timestamp(new Date())
				          .build());
			
		}
	}
	
	@PutMapping("/{hotelId}")
	public ResponseEntity<?> updateHotelDetail(@PathVariable Long hotelId ,@RequestBody @Valid HotelDto hotelDto){
		
		try {
			return ResponseEntity.ok(hotelService.updateHotel(hotelId, hotelDto));
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().body(ResponseDto.builder()
				          .message(e.getMessage())
				          .status(HttpStatus.BAD_REQUEST)
				          .timestamp(new Date())
				          .build());
			
		}
	}
	
	@DeleteMapping("/{hotelId}")
	public ResponseEntity<?> deleteHotelDetail(@PathVariable Long hotelId){
		
		try {
			return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
		} catch (Exception e) {
			
			return ResponseEntity.badRequest().body(ResponseDto.builder()
				          .message(e.getMessage())
				          .status(HttpStatus.BAD_REQUEST)
				          .timestamp(new Date())
				          .build());
			
		}
		
	}

}
