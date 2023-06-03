package com.blogApp.api.service.serviceImpl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApp.api.entities.User;
import com.blogApp.api.exceptions.ResourceNotFoundException;
import com.blogApp.api.payloads.UserDto;
import com.blogApp.api.repository.UserRepo;
import com.blogApp.api.service.UserService;

@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public UserDto createUser(UserDto userDto) {
		// TODO Auto-generated method stub
		User user= this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		
		return this.UserToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id",userId));
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		User updatedUser= this.userRepo.save(user);
		UserDto userDto1 = this.UserToUserDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		return this.UserToUserDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users= this.userRepo.findAll();
		List<UserDto> collectedUsers = users.stream().map(user->this.UserToUserDto(user)).collect(Collectors.toList());
		return collectedUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
		

	}
	private User dtoToUser(UserDto userDto)
	{
		User user = this.mapper.map(userDto, User.class);
		 
		return user;
	}
	private UserDto UserToUserDto(User user)
	{
		UserDto userDto = this.mapper.map(user,UserDto.class  );
		
		return userDto;
	}

}
