package com.ratingservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ratingservice.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

	public Optional<List<Rating>> findByUserId(Long userId);
	public Optional<List<Rating>> findByHotelId(Long hotelId);
	
}

