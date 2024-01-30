package com.ratingservice.serviceImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ratingservice.dto.RatingDto;
import com.ratingservice.dto.ResponseDto;
import com.ratingservice.entity.Rating;
import com.ratingservice.exception.ResourceNotFoundExeption;
import com.ratingservice.repository.RatingRepository;
import com.ratingservice.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Rating> getAllRating() {
		return ratingRepo.findAll();
	}

	@Override
	public Rating getRatingById(Long ratingId) {
		// TODO Auto-generated method stub
		return ratingRepo.findById(ratingId).orElseThrow(() -> new ResourceNotFoundExeption());
	}

	@Override
	public List<Rating> getRatingByHotelId(Long hotelId) {
		// TODO Auto-generated method stub
		return ratingRepo.findByHotelId(hotelId).orElseThrow(() -> new ResourceNotFoundExeption());
	}

	@Override
	public List<Rating> getRatingByUserId(Long userId) {
		// TODO Auto-generated method stub
		return ratingRepo.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundExeption());
	}

	@Override
	public ResponseDto addRating(RatingDto ratingDto) throws Exception {
		// TODO Auto-generated method stub
		Rating getRating = Rating.builder().hotelId(ratingDto.getHotelId()).userId(ratingDto.getUserId())
				.feedback(ratingDto.getFeedback()).rating(ratingDto.getRating()).build();
//		System.err.println(getRating);
		if (ratingRepo.save(getRating) == null) {
			throw new Exception("Something went wrong!!");
		}

		return ResponseDto.builder().message("rating added!").status(HttpStatus.CREATED).timestamp(new Date()).build();
	}

	@Override
	public ResponseDto updateRating(Long ratingId, RatingDto ratingDto) throws Exception {
		Rating getRating = Rating.builder().hotelId(ratingDto.getHotelId()).userId(ratingDto.getUserId())
				.feedback(ratingDto.getFeedback()).rating(ratingDto.getRating()).build();
		getRating.setId(ratingId);
		if (ratingRepo.save(getRating) == null) {
			throw new Exception("Something went wrong!!");
		}

		return ResponseDto.builder().message("rating updated!").status(HttpStatus.CREATED).timestamp(new Date())
				.build();

	}

	@Override
	public ResponseDto deleteRating(Long ratingId) {
		ratingRepo.findById(ratingId).orElseThrow(() -> new ResourceNotFoundExeption());
		ratingRepo.deleteById(ratingId);
		return ResponseDto.builder().message("rating deleted!").status(HttpStatus.OK).timestamp(new Date()).build();
	}

}
