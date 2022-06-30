package com.example.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdatePostRequest {
	
	@NotNull(message = "ID is required")
	private Long id;
	
	@NotBlank(message = "Title is required")
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
}

