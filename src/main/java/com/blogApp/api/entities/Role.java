package com.blogApp.api.entities;




import lombok.Data;

@jakarta.persistence.Entity
@Data
public class Role {

	@jakarta.persistence.Id	
	@jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.AUTO)
	private int id;
	
	private String name;
	
}