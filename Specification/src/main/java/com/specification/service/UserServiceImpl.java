package com.specification.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.specification.model.User;
import com.specification.model.UserRequest;
import com.specification.model.UserResponseDto;
import com.specification.model.UserResponseList;
import com.specification.repo.UserRepo;
import com.specification.specification.UserSpecification;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private UserSpecification userSpec;
	 
	/*
	 * @Value("${pagination.page.size.default}") private Integer defaultPageSize;
	 */
	
	@Override
	public List<User> getUserList(User request) {
//		List<User> list = userRepo.findAll(UserSpecification.getUsers(request));
		List<User> res = userRepo.findByFirstNameAndEmail("atheeq", "atheeq@gmail.com");
		Page<User> pages = null;

		/*
		 * pages = new PageImpl<>(userRepo.findAll(userSpec.getUsers(request)));
		 * Pageable paging = PageRequest.of(request.getPageNo() - 1,
		 * request.getPageSize()); pages = userRepo.findAll(userSpec.getUsers(request),
		 * paging);
		 * 
		 * if (pages != null && pages.getContent() != null) { list = pages.getContent();
		 * if (list != null && list.size() > 0) { UserResponseList respList = new
		 * UserResponseList(); respList.setTotalPages(pages.getTotalPages());
		 * respList.setTotalCount(pages.getTotalElements());
		 * respList.setPageNo(pages.getNumber() + 1); respList.setUserDto(new
		 * ArrayList<UserResponseDto>()); for (User users : list) { UserResponseDto obj
		 * = new UserResponseDto(); obj.populateObject(users);
		 * respList.getUserDto().add(obj); } return respList; } }
		 */
		return res;
	}

}
