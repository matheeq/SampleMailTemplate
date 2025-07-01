package com.specification.model;

import java.util.List;

public class UserResponseList {

	private long totalCount;
	private int totalPages;
	private int pageNo;
	private User user;
	private List<UserResponseDto> userDto;
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public List<UserResponseDto> getUserDto() {
		return userDto;
	}
	public void setUserDto(List<UserResponseDto> userDto) {
		this.userDto = userDto;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
		
}
