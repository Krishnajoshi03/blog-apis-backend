package com.blogApp.api.payloads;

import com.blogApp.api.entities.Post;
import com.blogApp.api.entities.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CommentDto {
	
	private int id;
	
	private String content;
	

}
