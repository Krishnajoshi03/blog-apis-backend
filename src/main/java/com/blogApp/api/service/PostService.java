package com.blogApp.api.service;

import java.util.List;

import com.blogApp.api.entities.Post;
import com.blogApp.api.payloads.PostDto;
import com.blogApp.api.payloads.PostResponse;

public interface PostService {
	
	
	void deletePost(Integer postId);
	
	PostDto getPostById(Integer postId); 
	
	

	List<PostDto> getPostByCategory(Integer categoryId);
	
	List<PostDto> getPostByUser(Integer userId );

	PostDto createPost(PostDto dto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto dto, Integer postId);


	List<PostDto> searchposts(String keyword);
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	

}
