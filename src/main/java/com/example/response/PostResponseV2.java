package com.example.response;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Comment;
import com.example.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseV2 {

	private Long id;
	private String title;
	private String description;
	private String content;
	private List<CommentResponse> comments;
	private List<String> tags;
	
	public PostResponseV2(Post post) {
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
