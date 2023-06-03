package com.blogApp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.api.payloads.ApiResponse;
import com.blogApp.api.payloads.CommentDto;
import com.blogApp.api.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {
	@Autowired
	CommentService commentService;

	@PostMapping("/posts/{postId}/comments/user/{userId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId,@PathVariable Integer userId)
	{
		CommentDto savedComment = this.commentService.createComment(commentDto,postId ,userId);
		return new  ResponseEntity<CommentDto>(savedComment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted with Id "+commentId,true),HttpStatus.OK);
	}
}
