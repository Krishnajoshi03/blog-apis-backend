package com.blogApp.api.payloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Email;

import com.blogApp.api.entities.Comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min=4,message = "UserName must be min of 4 characters")
	private String name;
	@jakarta.validation.constraints.Email(message = "Email is Not Valid!")
	private String email;
	@NotEmpty
	@Size(min = 4,max=10,message = "Password must be min 3 characters and maximum of 10 chars!")
	private String password;
	@NotEmpty
	private String about;
	
//	private List<PostDto> posts = new ArrayList<>();
//	
//	private List<CommentDto> comments =new ArrayList<>();
}
