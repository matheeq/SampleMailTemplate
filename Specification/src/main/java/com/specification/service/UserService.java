package com.specification.service;

import java.util.List;

import com.specification.model.User;

public interface UserService {
	
	public List<User> getUserList(User userRequest);

}
