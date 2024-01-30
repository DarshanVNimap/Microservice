package com.hotelservice.serviceImpl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hotelservice.dto.HotelDto;
import com.hotelservice.dto.ResponseDto;
import com.hotelservice.entity.Hotel;
import com.hotelservice.exception.ResourceNotFoundException;
import com.hotelservice.repository.HotelRepository;
import com.hotelservice.service.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

	@Autowired
	private HotelRepository hotelRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepo.findAll();
	}

	@Override
	public Hotel getHotel(Long hoteId) {
		return hotelRepo.findById(hoteId).orElseThrow(() -> new ResourceNotFoundException());
	}

	@Override
	public ResponseDto addHotel(HotelDto hotel) throws Exception {

		Hotel getHotelDetails = modelMapper.map(hotel, Hotel.class);

		if (hotelRepo.save(getHotelDetails) == null) {
			throw new Exception("Something went wrong!!");
		}

		return ResponseDto.builder()
				          .message("Hotel details added!")
				          .status(HttpStatus.CREATED)
				          .timestamp(new Date())
				          .build();
	}

	@Override
	public ResponseDto updateHotel(Long hotelId, HotelDto hoteldto) throws Exception {
		Hotel getHotel = modelMapper.map(hoteldto, Hotel.class);
		getHotel.setId(hotelId);
		if (hotelRepo.save(getHotel) == null) {
			throw new Exception("Something went wrong!!");
		}

		return ResponseDto.builder()
				          .message("Hotel details updated!")
				          .status(HttpStatus.CREATED)
				          .timestamp(new Date())
				          .build();

	}

	@Override
	public ResponseDto deleteHotel(Long hotelId) {
		hotelRepo.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException());
		hotelRepo.deleteById(hotelId);
		return ResponseDto.builder()
		          .message("Hotel details deleted!")
		          .status(HttpStatus.OK)
		          .timestamp(new Date())
		          .build();
	}

}
