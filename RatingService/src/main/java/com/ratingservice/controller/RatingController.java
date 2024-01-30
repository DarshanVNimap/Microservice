package com.ratingservice.controller;

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

import com.ratingservice.dto.RatingDto;
import com.ratingservice.dto.ResponseDto;
import com.ratingservice.service.RatingService;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllReting(){
		return ResponseEntity.ok(ratingService.getAllRating());
	}
	
	@GetMapping("/all/hotel/{hotelId}")
	public ResponseEntity<?> getRatingByHotelId(@PathVariable Long hotelId){
		return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
	}
	 
	@GetMapping("/all/user/{userId}")
	public ResponseEntity<?> getRatingByUserId(@PathVariable Long userId){
		return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
	}
	 
	@GetMapping("/{ratingId}")
	public ResponseEntity<?> getRatingById(@PathVariable Long ratingId){
		return ResponseEntity.ok(ratingService.getRatingById(ratingId));
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> addRating(@RequestBody RatingDto ratingDto){
		try {
			return ResponseEntity.ok(ratingService.addRating(ratingDto));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseDto.builder().message(e.getMessage())
										 .status(HttpStatus.BAD_REQUEST)
										 .timestamp(new Date())
										 .build()
					);
		}
	}
	
	@PutMapping("/{ratingId}")
	public ResponseEntity<?> updateRating(@PathVariable Long ratingId , @RequestBody RatingDto ratingDto){
		try {
			return ResponseEntity.ok(
					ratingService.updateRating(ratingId, ratingDto)
					);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					ResponseDto.builder().message(e.getMessage())
										 .status(HttpStatus.BAD_REQUEST)
										 .timestamp(new Date())
										 .build()
					);
		}
	}
	
	@DeleteMapping("/{ratingId}")
	public ResponseEntity<?> deleteRating(@PathVariable Long ratingId){
		return ResponseEntity.ok(ratingService.deleteRating(ratingId));
	}

}
