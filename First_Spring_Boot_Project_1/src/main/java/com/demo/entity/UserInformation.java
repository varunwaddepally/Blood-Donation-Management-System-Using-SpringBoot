package com.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity 
public class UserInformation {
	
	private String username;
	@Id
	private String emailid;
	@Column(unique = true, nullable = false) 
   	private String password;
	@Column(unique = true,nullable = false)
	private long mobilenumber;
	private String gender;
	private String address;
}
