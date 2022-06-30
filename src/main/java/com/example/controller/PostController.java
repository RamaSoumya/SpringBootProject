package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Post;
import com.example.entity.PostPaginationCustomization;
import com.example.request.CreatePostRequest;
import com.example.request.InQueryRequest;
import com.example.request.UpdatePostRequest;
import com.example.response.PostResponse;
import com.example.response.PostResponseV2;
import com.example.service.PostService;
import com.examples.utils.AppConstants;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;

//@Api(value = "CRUD REST APIs for Post Resource")
@RestController
@RequestMapping()
public class PostController {
	
	@Autowired
	PostService postService;
	
	//@ApiOperation(value = "Get All Posts REST API")
	@GetMapping("/api/v1/post/getAll")
	public List<PostResponse> getAllPost () {
		
		List<Post> postList = postService.getAllPost();
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
		
		postList.stream().forEach( post -> {
			postResponseList.add(new PostResponse(post));
		});
		
		return postResponseList;
	}
	
	//@ApiOperation(value = "Get by ID Post REST API")
	//versioning through URI path
	@GetMapping("/api/v1/post/getPostById/{id}")
	//versioning through query
	//@GetMapping(value = "/api/post/getPostById/{id}", params = "version-1")
	//versioning through custom header
	//@GetMapping(value = "/api/post/getPostById/{id}", header = "X-API-VERSION-1")
	//versioning through content negotiation
	//@GetMapping(value = "/api/post/getPostById/{id}", produces = -"application/vnd.javaguides.v1+json")
	public Post getPostByIdV1 (@PathVariable Long id) {
		Post post = postService.getPostById(id);
		return post;
	}
	
	//versioning through URI path
	@GetMapping("/api/v2/post/getPostById/{id}")
	//versioning through query
	//@GetMapping(value = "/api/post/getPostById/{id}", params = "version-1")
	//versioning through custom header
	//@GetMapping(value = "/api/post/getPostById/{id}", header = "X-API-VERSION-2")
	//versioning through content negotiation
	//@GetMapping(value = "/api/post/getPostById/{id}", produces = -"application/vnd.javaguides.v2+json")
	public PostResponseV2 getPostByIdV2 (@PathVariable Long id) {
		Post post = postService.getPostById(id);
		PostResponseV2 postResponseV2 = new PostResponseV2();
		postResponseV2.setId(post.getId());
		postResponseV2.setTitle(post.getTitle());		
		postResponseV2.setContent(post.getContent());
		postResponseV2.setDescription(post.getDescription());
		postResponseV2.setContent(post.getContent());
		
		List<String> tags = new ArrayList<>();
		tags.add("Java");
		tags.add("Spring Boot");
		tags.add("AWS");
		postResponseV2.setTags(tags);
		return postResponseV2;
	}
	
//	@ApiOperation(value = "Create Post REST API")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/api/v1/post/create")
	public PostResponse createPost (@Valid @RequestBody CreatePostRequest createPostRequest) {
		Post post = postService.createPost(createPostRequest);
		return new PostResponse(post);
	}
	
//	@ApiOperation(value = "Update Post REST API")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/api/v1/post/updatetitle")
	public PostResponse updatePostTitle (@Valid @RequestBody UpdatePostRequest updatePostRequest) {
		Post post = postService.updatePostTitle(updatePostRequest);
		return new PostResponse(post);
	}
	
//	@ApiOperation(value = "Update Post REST API")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/api/v1/post/updateContent")
	public PostResponse updatePostContent (@Valid @RequestBody UpdatePostRequest updatePostRequest) {
		Post post = postService.updatePostContent(updatePostRequest);
		return new PostResponse(post);
	}
	
//	@ApiOperation(value = "Delete Post REST API")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/api/v1/post/delete/{id}")
	public String deletePost (@PathVariable long id) {
		return postService.deletePost(id);
	}
	
	//Pagination without customization
	//pagination using JPA
//	@GetMapping("getPostsWithPagintion")
//	public List<PostResponse> getAllPostsWithPagination (@RequestParam int pageNo, @RequestParam int pageSize ) {
//		List<Blog> postList = postService.getAllBlogWithPagination(pageNo, pageSize);
//		List<BlogPaginationCustomizationResponse> postResponseList = new ArrayList<BlogPaginationCustomizationResponse>();
//					
//		postList.stream().forEach(post -> {
//			postResponseList.add(new PostResponse(post));
//		});
//		
//		postList.
//
//		return postResponseList;	
//	}
	
	//Pagination with customization
	@GetMapping("/api/v1/post/getPostsWithPagintion")
	public PostPaginationCustomization getAllBlogsWithPagination (@RequestParam int pageNo, @RequestParam int pageSize ) {
		return postService.getAllPostWithPagination(pageNo, pageSize);	
	}
	
	@GetMapping("/api/v1/post/getPostsWithPaginationAndSorting")
	public PostPaginationCustomization getAllPostsWithPaginationAndSorting(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		return postService.getAllPostWithPaginationAndSorting(pageNo, pageSize, sortBy, sortDir);
	}
	
	//sorting asc using JPA
	@GetMapping("/api/v1/post/getAllPostWithSortingAsc")
	public List<PostResponse> getAllBlogWithSorting () {
		List<Post> postList = postService.getAllPostWithSortingAsc();
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
							
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});

		return postResponseList;		
	}
		
	//sorting desc using JPA
	@GetMapping("/api/v1/post/getAllWithSortingDesc")
	public List<PostResponse> getAllBlogWithSortingDes () {
		List<Post> postList = postService.getAllPostWithSortingDesc();
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
							
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});

		return postResponseList;		
	}
	
	@GetMapping("/api/v1/post/getPostByTitle/{title}")
	public List<PostResponse> getPOstByTitle (@PathVariable String title) {
		List<Post> postList = postService.getPostByTitle(title);
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
		
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});
		
		return postResponseList;
	}
	
	@GetMapping("/api/v1/post/getPostByTitleAndDescription/{title}/{description}") 
	public PostResponse getPostTitleAndDescription (@PathVariable String title, @PathVariable String description) {
		return new PostResponse(postService.getPostByTitleAndDescription(title, description));
	}
	
	@GetMapping("/api/v1/post/getByTitleOrDescription/{title}/{description}") 
	public List<PostResponse> getByTitleOrDescription (@PathVariable String title, @PathVariable String description) {
		List<Post> postList = postService.getPostByTitleOrDescription(title, description);
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
		
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});
		
		return postResponseList;
	}
	
	@GetMapping("/api/v1/post/getByDescriptionIn")
	public List<PostResponse> getByDescriptionIn (@RequestBody InQueryRequest inQueryRequest) {
		List<Post> postList = postService.getPostByDescriptionIn(inQueryRequest);
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
		
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});
		
		return postResponseList;
	}
	
	@GetMapping("/api/v1/post/like/{title}")
	public List<PostResponse> like (@PathVariable String title) {
		List<Post> postList = postService.like(title);
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
		
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});
		
		return postResponseList;
	}
	
	@GetMapping("/api/v1/post/startsWith/{title}")
	public List<PostResponse> startsWith (@PathVariable String title) {
		List<Post> postList = postService.like(title);
		List<PostResponse> postResponseList = new ArrayList<PostResponse>();
		
		postList.stream().forEach(post -> {
			postResponseList.add(new PostResponse(post));
		});
		
		return postResponseList;
	}
	
	@PutMapping("/api/v1/post/updatePostByTitle/{id}/{title}")
	public String updateWithJpql (@PathVariable Long id, @PathVariable String title) {
		return postService.updatePostWithJpql(id, title)+ " " + "Post(s) updated";
	}
	
	@DeleteMapping("/api/v1/post/deletePostByTitle/{title}")
	public String deleteWithJpql (@PathVariable String title) {
		return postService.deletePostWithJpql(title)+ " " + "Post(s) deleted";
	}
}
