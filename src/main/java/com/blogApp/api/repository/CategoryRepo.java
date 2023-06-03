package com.blogApp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.api.entities.Category;
import com.blogApp.api.payloads.CategoryDto;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

	
}
