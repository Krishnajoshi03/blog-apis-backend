package com.blogApp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.api.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{
	
}
