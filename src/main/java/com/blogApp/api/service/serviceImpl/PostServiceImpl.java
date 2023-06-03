package com.blogApp.api.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogApp.api.entities.Category;
import com.blogApp.api.entities.Post;
import com.blogApp.api.entities.User;
import com.blogApp.api.exceptions.ResourceNotFoundException;
import com.blogApp.api.payloads.PostDto;
import com.blogApp.api.payloads.PostResponse;
import com.blogApp.api.repository.CategoryRepo;
import com.blogApp.api.repository.PostRepo;
import com.blogApp.api.repository.UserRepo;
import com.blogApp.api.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private PostRepo postRepo;
	
	@Override
	public PostDto createPost(PostDto dto,Integer userId,Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Category category= this.categoryRepo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "Id",categoryId));
		Post post= this.mapper.map(dto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post postAdded= this.postRepo.save(post);
		
		return  this.mapper.map(postAdded, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto dto,Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(dto.getTitle());
		post.setContent(dto.getContent());
		post.setImageName(dto.getImageName());
		Post save = this.postRepo.save(post);
		return this.mapper.map(save, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		this.postRepo.delete(post);
		
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber ,Integer pageSize,String sortBy,String sortDir) {
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc"))
		{
			sort=Sort.by(sortBy).ascending();
		}
		else
		{
			sort=Sort.by(sortBy).descending();
		}
		Pageable  p = PageRequest.of(pageNumber, pageSize,sort);
		
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		List<Post> posts = pagePost.getContent();
		List<PostDto> dtos = posts.stream().map(
				(post)->this.mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(dtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLast(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts= this.postRepo.findByCategory(category);
		
		List<PostDto> dtos=posts.stream().map((t)-> this.mapper.map(t, PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> dtos = posts.stream().map((post)-> this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return dtos;
	}

	
	@Override
	public List<PostDto> searchposts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> dtos = posts.stream().map((post)->this.mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return dtos;
	}

}
