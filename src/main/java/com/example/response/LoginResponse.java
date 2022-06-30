package com.example.response;

import com.example.entity.Login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	private String usernameOrEmail;
	private String password;
	
	public LoginResponse(Login login) {
		this.usernameOrEmail = login.getUsernameOrEmail();
		this.password = login.getPassword();
	}
	
}
