package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Roles;
import com.example.entity.User;

import lombok.Data;

@Data
public class UserResponse {
	
	private long id;
	private String name;
	private String username;
	private String email;
	private String password;
	private List<RolesResponse> roles;
	
	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		
		if(user.getRoles() != null) {
			roles = new ArrayList<RolesResponse>();
			for(Roles role : user.getRoles()) {
				roles.add(new RolesResponse(role));
			}
		}
	}
}
