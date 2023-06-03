package com.blogApp.api.payloads;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class JwtAuthResponse {
	
	private String  token;
	private UserDto user;
	

}
