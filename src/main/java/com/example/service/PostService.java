package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.entity.PostPaginationCustomization;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.request.CreateCommentRequest;
import com.example.request.CreatePostRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdatePostRequest;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepo;
	
	@Autowired
	CommentRepository commentRepo;
	
	public List<Post> getAllPost() {
		return postRepo.findAll();
	}
	
	public Post getPostById(Long id) {
		Post blog = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
		return blog;
	}
	
	@Secured("ROLE_ADMIN")
	public Post createPost (CreatePostRequest createPostRequest) {
		Post post = new Post(createPostRequest);
		post = postRepo.save(post);
		
		List<Comment> commentList = new ArrayList<>();
		
		if(createPostRequest.getPostComments() != null) {
			for(CreateCommentRequest createCommentRequest : createPostRequest.getPostComments()) {
				Comment comment = new Comment();
				comment.setName(createCommentRequest.getName());
				comment.setEmail(createCommentRequest.getEmail());
				comment.setBody(createCommentRequest.getBody());
				//setting foreign key
				comment.setPost(post);
				
				commentList.add(comment);
			}
			commentRepo.saveAll(commentList);
		}
		post.setComments(commentList);
		
		return post;
	}
	
	@Secured("ROLE_ADMIN")
	public Post updatePostTitle (UpdatePostRequest updatePostRequest) {
		Post post = postRepo.findById(updatePostRequest.getId()).get();
		
		if(updatePostRequest.getTitle() != null &&
				!updatePostRequest.getTitle().isEmpty()) {
			post.setTitle(updatePostRequest.getTitle());
		}
		
		post = postRepo.save(post);
		
		return post;
	}
	
	@Secured("ROLE_ADMIN")
	public Post updatePostContent (UpdatePostRequest updatePostRequest) {
		Post post = postRepo.findById(updatePostRequest.getId()).get();
		
		if(updatePostRequest.getContent() != null &&
				!updatePostRequest.getContent().isEmpty()) {
			post.setContent(updatePostRequest.getContent());
		}
		
		post = postRepo.save(post);
		
		return post;
	}
	
	@Secured("ROLE_ADMIN")
	public String deletePost (Long id) {
		postRepo.deleteById(id);
		return "Post has been deleted successfully";
	}
	
	//Pagination with customization
	public PostPaginationCustomization getAllPostWithPagination (int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		Page<Post> posts = postRepo.findAll(pageable);
		//to get list of entity class we use getContent() and the below line is used without customization in pagination
		//return postRepo.findAll(pageable).getContent();
		List<Post> content = postRepo.findAll(pageable).getContent();
		
		PostPaginationCustomization pg = new PostPaginationCustomization();
		pg.setContent(content);
		pg.setPageNo(posts.getNumber());
		pg.setPageSize(posts.getSize());
		pg.setTotalElements(posts.getTotalElements());
		pg.setTotalPages(posts.getTotalPages());
		pg.setLast(posts.isLast());
		
		return pg;
	}
	
	//Pagination without customization
//	public List<Post> getAllPostWithPagination (int pageNo, int pageSize) {
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
//		//to get list of entity class we use getContent() 
//		return postRepo.findAll(pageable).getContent();
//	}
	
	//Pagination with customization
	public PostPaginationCustomization getAllPostWithPaginationAndSorting (int pageNo, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
				? Sort.by(sortBy).ascending() 
				: Sort.by(sortBy).descending() ;
		
		//Creating Pageable instance
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		Page<Post> posts = postRepo.findAll(pageable);
		//to get list of entity class we use getContent() and the below line is used without customization in pagination
		//return postRepo.findAll(pageable).getContent();
		List<Post> content = postRepo.findAll(pageable).getContent();
			
		PostPaginationCustomization pg = new PostPaginationCustomization();
		pg.setContent(content);
		pg.setPageNo(posts.getNumber());
		pg.setPageSize(posts.getSize());
		pg.setTotalElements(posts.getTotalElements());
		pg.setTotalPages(posts.getTotalPages());
		pg.setLast(posts.isLast());
			
		return pg;
	}
	
	public List<Post> getAllPostWithSortingAsc() {
		Sort sort = Sort.by(Sort.Direction.ASC, "description");
		return postRepo.findAll(sort);
	}
	
	public List<Post> getAllPostWithSortingDesc() {
		Sort sort = Sort.by(Sort.Direction.DESC, "description");
		return postRepo.findAll(sort);
	}
	
	public List<Post> getPostByTitle(String title){
		return postRepo.findPostByTitle(title);
	}
	
	public Post getPostByTitleAndDescription(String title, String description) {
		//return postRepo.findByTitleAndDescription(title, description);
		return postRepo.getPostByTitleAndDescription(title, description);
	}
	
	public List<Post> getPostByTitleOrDescription(String title, String description) {
		return postRepo.findPostByTitleOrDescription(title, description);
	}
	
	public List<Post> getPostByDescriptionIn(InQueryRequest inQueryRequest) {
		return postRepo.findPostByDescriptionIn(inQueryRequest.getDescriptions());
	}
	
	public List<Post> like(String title) {
		return postRepo.findPostByTitleContains(title);
	}
	
	public List<Post> startsWith(String title) {
		return postRepo.findPostByTitleStartsWith(title);
	}
	
	public Integer updatePostWithJpql(Long id, String title) {
		return postRepo.UpdatePostByTitle(id, title);
	}
	
	public Integer deletePostWithJpql(String title) {
		return postRepo.DeletePostByTitle(title);
	}
}
