package com.identityService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
	
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private LocalDateTime createdAt;

}
