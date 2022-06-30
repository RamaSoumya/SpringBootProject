package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Comment;
import com.example.exceptions.BlogApiException;
import com.example.request.CreateCommentRequest;
import com.example.request.UpdateCommentRequest;
import com.example.response.CommentResponse;
import com.example.service.CommentService;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

//@Api(value = "CRUD REST APIs for Comment Resource")
@RestController
@RequestMapping("api/comment/")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	//@ApiOperation(value = "Create comment REST API")
	@PostMapping("createComment/{postId}/comment")
	public CommentResponse createComment(@PathVariable long postId, @Valid @RequestBody CreateCommentRequest createCommentRequest) {
		Comment comment = commentService.createPost(postId, createCommentRequest);
		return new CommentResponse(comment);
	}
	
	//@ApiOperation(value = "Get Comments by Post ID REST API")
	@GetMapping("getCommentsById/{postId}/comment")
	public List<CommentResponse> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
		List<Comment> commentList = commentService.getCommentsById(postId);
		List<CommentResponse> commentResponseList = new ArrayList<CommentResponse>();
		
		commentList.stream().forEach(comment -> {
			commentResponseList.add(new CommentResponse(comment));
		});
		
		return commentResponseList;
	}
	
	//@ApiOperation(value = "Get Comments by comment ID REST API")
	@GetMapping("getCommentsByCommentId/{postId}/comments/{commentId}")
	public CommentResponse getCommentByCommentId(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) throws BlogApiException {
		Comment comment = commentService.getCommentByCommentId(postId, commentId);
		return new CommentResponse(comment);
	}
	
	//@ApiOperation(value = "Update Comments REST API")
	@PutMapping("updateComment/{postId}/comments/{commentId}")
	public CommentResponse updateComment (@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId ,@Valid @RequestBody UpdateCommentRequest updateCommentRequest) throws BlogApiException {
		Comment comment = commentService.updateComment(postId, commentId, updateCommentRequest);
		return new CommentResponse(comment);
	}
	
	//@ApiOperation(value = "Delete Comments REST API")
	@DeleteMapping("deleteComment/{postId}/comments/{commentId}")
	public String deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) throws BlogApiException {
		return commentService.deleteComment(postId, commentId);
	}
}
