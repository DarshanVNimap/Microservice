package com.hotelservice.service;

import java.util.List;

import com.hotelservice.dto.HotelDto;
import com.hotelservice.dto.ResponseDto;
import com.hotelservice.entity.Hotel;

public interface HotelService {
	
	public List<Hotel> getAllHotel();
	public Hotel getHotel(Long hoteId);
	public ResponseDto addHotel(HotelDto hotel) throws Exception;
	public ResponseDto updateHotel(Long hotelId  , HotelDto hoteldto) throws Exception;
	public ResponseDto deleteHotel(Long hotelId);

}
