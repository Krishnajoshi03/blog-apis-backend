package com.blogApp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogApp.api.entities.Category;
import com.blogApp.api.entities.Post;
import com.blogApp.api.entities.User;
import com.blogApp.api.payloads.PostDto;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key")String keyword);
}
