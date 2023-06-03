package com.blogApp.api.service;

import org.springframework.stereotype.Service;

import com.blogApp.api.entities.Comment;
import com.blogApp.api.payloads.CommentDto;

import jakarta.websocket.server.ServerEndpoint;

@Service
public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);
	void deleteComment(Integer commentId);
	

}
