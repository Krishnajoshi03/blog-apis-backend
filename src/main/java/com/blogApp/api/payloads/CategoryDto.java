package com.blogApp.api.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
	private Integer categoryId;
	@NotEmpty
	@Size(min=4,max = 20,message = "category title must be atleast 5 char and maximum 20")
	private String categoryTitle;
	@NotEmpty
	@Size(min = 8,max=40 )
	private String categoryDescription;

}
