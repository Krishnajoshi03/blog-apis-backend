package com.blogApp.api.service.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.api.entities.Comment;
import com.blogApp.api.entities.Post;
import com.blogApp.api.entities.User;
import com.blogApp.api.exceptions.ResourceNotFoundException;
import com.blogApp.api.payloads.CommentDto;
import com.blogApp.api.repository.CommentRepo;
import com.blogApp.api.repository.PostRepo;
import com.blogApp.api.repository.UserRepo;
import com.blogApp.api.service.CommentService;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	PostRepo postRepo;
	@Autowired 
	CommentRepo  commentRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	UserRepo userRepo;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Comment map = this.mapper.map(commentDto, Comment.class);
		map.setPost(post);
		map.setUser(user);
		
		Comment comment=this.commentRepo.save(map);
		return this.mapper.map(comment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment c = commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment", "Id", commentId));
		commentRepo.delete(c);
	}


	
	
	
	

}
