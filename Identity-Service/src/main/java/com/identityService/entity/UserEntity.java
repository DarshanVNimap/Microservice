package com.identityService.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	@CreationTimestamp
	private LocalDateTime createdAt;

}
