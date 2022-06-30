package com.example.request;

//import java.util.List;
import java.util.Set;

import com.example.entity.Roles;

import lombok.Data;

@Data
public class CreateUserRequest {

	private Long id;
	private String name;
	private String username;
	private String password;
	private String email;
	private Set<Roles> roles;
	
}
