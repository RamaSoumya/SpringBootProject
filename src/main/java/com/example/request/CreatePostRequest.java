package com.example.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
@Data
public class CreatePostRequest {

	@NotBlank(message = "Title is required")
	@Size(min = 2, message = "Post title should have at least 2 characters")
	private String title;
	
	@NotEmpty
	@Size(min = 10, message = "Post description should have at least 10 characters")
	private String description;
	
	@NotEmpty
	private String content;
	
	private List<CreateCommentRequest> postComments;
}
