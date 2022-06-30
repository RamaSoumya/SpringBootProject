package com.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.request.CreatePostRequest;

import lombok.Data;
//import lombok.Getter;
import lombok.NoArgsConstructor;
//import lombok.Setter;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "content")
	private String content;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL /*oprhanRemoval = true*/)
	private List<Comment> comments;
	
	public Post(CreatePostRequest createPostRequest) {
		this.title = createPostRequest.getTitle();
		this.description = createPostRequest.getDescription();
		this.content = createPostRequest.getContent();
	}
}
