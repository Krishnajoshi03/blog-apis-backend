package com.blogApp.api.service;

import java.util.List;

import com.blogApp.api.payloads.CategoryDto;

public interface CategoryService {
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer catId);
	public void deleteCategory(Integer categoryId);
	public  CategoryDto getCategory(Integer categoryId);
	public List<CategoryDto> getAllCategory();

}
