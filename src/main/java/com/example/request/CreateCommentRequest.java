package com.example.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateCommentRequest {
	
	private long id;
	
	@NotEmpty(message = "Name should not be null or empty")
	private String name;
	
	@NotEmpty(message = "Must be well formed Email address")
	@Email
	private String email;
	
	@NotEmpty(message = "Comment body should not be null or empty")
	@Size(min = 10, message = "Comment body should be minimum 10 characters")
	private String body;
	//private Post post;
	
}
