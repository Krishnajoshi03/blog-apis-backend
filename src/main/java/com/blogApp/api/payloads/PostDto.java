package com.blogApp.api.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Comments;

import com.blogApp.api.entities.Category;
import com.blogApp.api.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {
	Integer postId;
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	

	private CategoryDto category;
	

	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();

}
