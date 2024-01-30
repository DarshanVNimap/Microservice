package com.ratingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RatingDto {
	
	@NotBlank(message = "please enter user id")
	private Long userId;
	@NotBlank(message = "please enter user id")
	private Long hotelId;
	@NotBlank(message = "please enter user id")
	private Float rating ;
	@Size(max = 255 , message = "feedback length should be 255 character")
	private String feedback;

}
