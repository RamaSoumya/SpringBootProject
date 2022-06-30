package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.entity.Comment;
import com.example.entity.Post;
import com.example.exceptions.BlogApiException;
import com.example.exceptions.ResourceNotFoundException;
import com.example.repository.CommentRepository;
import com.example.repository.PostRepository;
import com.example.request.CreateCommentRequest;
import com.example.request.UpdateCommentRequest;

@Service
public class CommentService {

	@Autowired
	CommentRepository commentRepo;
	
	@Autowired
	PostRepository postRepo;
	
	public Comment createPost (long postId, CreateCommentRequest createCommentRequest) {
		Comment comment = new Comment(createCommentRequest);
		//retrieve post by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
		//set post to comment entity
		comment.setPost(post);
		//save comment entity to DB
		Comment newComment = commentRepo.save(comment);
		
		return newComment;
	}
	
	public List<Comment> getCommentsById(long postId) {
		List<Comment> commentList = commentRepo.findByPostId(postId);
		return commentList;
	}
	
	public Comment getCommentByCommentId(long postId, long commentId) throws BlogApiException {
		//retrieve post by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
		//retrieve comment by commentId
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Post","id",commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment doesnot belong to post");
		}
		return comment;
	}
	
	public Comment updateComment (long postId, long commentId, UpdateCommentRequest updateCommentRequest) throws BlogApiException {
		
		//retrieve post by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
		//retrieve comment by commentId
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Post","id",commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		
		comment.setBody(updateCommentRequest.getBody());
		comment.setEmail(updateCommentRequest.getEmail());
		comment.setName(updateCommentRequest.getName());
		
		Comment updatedComment = commentRepo.save(comment);
		
		return updatedComment;
	}
	
	public String deleteComment(long postId, long commentId) throws BlogApiException {
		
		//retrieve post by postId
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
		//retrieve comment by commentId
		Comment comment = commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Post","id",commentId));
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment does not belongs to post");
		}
		
		commentRepo.delete(comment);
		return "Comment has been deleted successfully";
	}
}
