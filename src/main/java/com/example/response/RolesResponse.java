package com.example.response;

import com.example.entity.Roles;

import lombok.Data;

@Data
public class RolesResponse {

	private Long id;
	private String name;
	
	public RolesResponse (Roles roles) {
		this.id = roles.getId();
		this.name = roles.getName();
	}
}
