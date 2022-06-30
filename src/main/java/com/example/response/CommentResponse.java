package com.example.response;

import com.example.entity.Comment;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//@ApiModel(description = "Comment model information")
@Data
public class CommentResponse {
	
	//@ApiModelProperty(value = "Blog Comment ID")
	private long id;
	//@ApiModelProperty(value = "Blog Comment name")
	private String name;
	//@ApiModelProperty(value = "Blog Comment email")
	private String email;
	//@ApiModelProperty(value = "Blog Comment body")
	private String body;
	
	public CommentResponse(Comment comment) {
		this.id = comment.getId();
		this.name = comment.getName();
		this.email = comment.getEmail();
		this.body = comment.getBody();
	}
}
