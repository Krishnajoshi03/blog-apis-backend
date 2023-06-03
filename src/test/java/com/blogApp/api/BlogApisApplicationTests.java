package com.blogApp.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.blogApp.api.repository.UserRepo;

@SpringBootTest
class BlogApisApplicationTests {

	@Autowired
	UserRepo repo;
	@Test
	void contextLoads() {
		System.out.println(repo.getClass().getName());
		System.out.println(repo.getClass().getPackageName());
	}

}
