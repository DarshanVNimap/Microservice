package com.ratingservice.service;

import java.util.List;

import com.ratingservice.dto.RatingDto;
import com.ratingservice.dto.ResponseDto;
import com.ratingservice.entity.Rating;

public interface RatingService {
	
	public List<Rating> getAllRating();
	public Rating getRatingById(Long ratingId);
	public List<Rating> getRatingByHotelId(Long hotelId);
	public List<Rating> getRatingByUserId(Long userId);
	public ResponseDto addRating(RatingDto ratingDto) throws Exception;
	public ResponseDto updateRating(Long ratingId , RatingDto ratingDto) throws Exception;
	public ResponseDto deleteRating(Long ratingId);

}
