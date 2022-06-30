package com.example.response;

import com.example.entity.SignUp;

import lombok.Data;

@Data
public class SignUpResponse {

	private String name;
	private String username;
	private String email;
	private String password;
	
	public SignUpResponse(SignUp signUp) {
		this.name = signUp.getName();
		this.username = signUp.getUsername();
		this.email = signUp.getEmail();
		this.password = signUp.getPassword();
	}
	
}

