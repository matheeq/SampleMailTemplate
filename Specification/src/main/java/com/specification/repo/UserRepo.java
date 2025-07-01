package com.specification.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.specification.model.User;

public interface UserRepo extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User>{
	
	public Page<User> findAll(Specification<User> spec, Pageable pageable);
	
	public List<User> findAll(Specification<User> spec);
	
	public List<User> findByFirstNameAndEmail(String name, String email);
	
	public List<User> findByFirstNameOrEmail(String name, String email);
 
}
