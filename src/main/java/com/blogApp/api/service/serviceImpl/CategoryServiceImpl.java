package com.blogApp.api.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.api.entities.Category;
import com.blogApp.api.exceptions.ResourceNotFoundException;
import com.blogApp.api.payloads.CategoryDto;
import com.blogApp.api.repository.CategoryRepo;
import com.blogApp.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	ModelMapper mapper;
	@Autowired
	CategoryRepo repo;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		 Category cat = this.DtoToCategory(categoryDto);
		 Category savedCat= repo.save(cat);
		
		return this.CategoryToDto(savedCat);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer catId) {
		Category category = this.repo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", catId));
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		return this.CategoryToDto(category);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=repo.findById(categoryId)
				.orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.repo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.repo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		return this.CategoryToDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.repo.findAll();
		List<CategoryDto> catDto=categories.stream().map((t) ->this.CategoryToDto(t)).collect(Collectors.toList());
		return catDto;
	}
	private Category DtoToCategory(CategoryDto categoryDto)
	{
		return this.mapper.map(categoryDto, Category.class);
	}
	private CategoryDto CategoryToDto(Category category)
	{
		return this.mapper.map(category,CategoryDto.class );
	}
}
