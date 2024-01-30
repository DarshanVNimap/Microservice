package com.hotelservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HotelDto {
	
	@NotBlank(message = "please enter hotel name")
	private String name;
	
	@NotBlank(message = "please enter location of hotel")
	private String location;
	
	@Size(max = 255 , message = "description max length should be 255 character")
	private String about;

}
