package com.blogApp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.api.config.Constants;
import com.blogApp.api.payloads.ApiResponse;
import com.blogApp.api.payloads.PostDto;
import com.blogApp.api.payloads.PostResponse;
import com.blogApp.api.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/posts")
	ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",defaultValue =Constants.PAGE_NUMBER,required = false)Integer pageNumber,
			@RequestParam( value = "pageSize",defaultValue = Constants.PAGE_SIZE,required = false ) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = Constants.SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = Constants.SORT_DIR,required = false) String sortDir
			)
	{
		return new ResponseEntity<PostResponse>(this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir),HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
	{
		return new ResponseEntity<PostDto>(this.postService.getPostById(postId),HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	ResponseEntity<PostDto> updateUserById(@RequestBody PostDto dto,@PathVariable Integer postId )
	{
		PostDto updatedPost= this.postService.updatePost(dto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}")
	ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>( new ApiResponse("Post Successfully Deleted  with Id"+postId,true),HttpStatus.OK);
	}
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	ResponseEntity<PostDto> createPost(
			@RequestBody PostDto dto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
	PostDto postDto= this.postService.createPost(dto, userId, categoryId) ;
	return new ResponseEntity<PostDto>(postDto,HttpStatus.CREATED);
	}

	
	@GetMapping("/user/{userId}/posts")
	ResponseEntity<List<PostDto> >getPostByUserId(@PathVariable Integer userId)
	{
		List<PostDto> posts= this.postService.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/posts")
	ResponseEntity<List<PostDto> >getPostByCategoryId(@PathVariable Integer categoryId)
	{
		List<PostDto> posts= this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/posts/search/{keyword}")
	ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword)
	{
		return new ResponseEntity<List<PostDto>>(this.postService.searchposts(keyword),HttpStatus.OK);
	}

}
