package com.specification.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.specification.feignDemo.FeignDemo;
import com.specification.model.User;
import com.specification.repo.UserRepo;
import com.specification.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FeignDemo feignDemo;
	
	@GetMapping("/get-users")
	public List<User> getUser(@RequestBody User request) {
		List<User> a = userService.getUserList(request);
		return a;
		
	}
	
	@PostMapping("/save-user")
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
	//get data by interacting MS
	@PostMapping("/get-data-from-ms")
	public String getData() {
		String a = feignDemo.getStaticData();
		return a;
	}
	 
	//get Dynamic data from MS
	@GetMapping("/get-dynamic-data-from-ms")
	public String getDynamicData(@RequestParam String name) {
		String data = feignDemo.getDynamicData(name);
		return data;
		
	}
	
	
}
