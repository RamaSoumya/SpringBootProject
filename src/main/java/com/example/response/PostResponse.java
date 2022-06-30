package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Comment;
import com.example.entity.Post;
//import com.example.request.CreateBlogRequest;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter

//@ApiModel(description = "Post model information")
@Data
public class PostResponse {
	
	//@ApiModelProperty(value = "Blog post ID")
	private Long id;
	
	//@ApiModelProperty(value = "Blog post title")
	private String title;
	
	//@ApiModelProperty(value = "Blog post description")
	private String description;
	
	//@ApiModelProperty(value = "Blog post content")
	private String content;
	
	//@ApiModelProperty(value = "Blog post comments")
	private List<CommentResponse> comments;
	
	public PostResponse(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.description = post.getDescription();
		this.content = post.getContent();
		
		if(post.getComments() != null) {
			comments = new ArrayList<CommentResponse>();
			for(Comment comment : post.getComments()) {
				comments.add(new CommentResponse(comment));
			}
		}
	}
}
