package com.FileOperations.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FileOperations.model.DTO;
import com.FileOperations.util.PrepareAndSendEmail;

@RestController
public class EmailTemplate {
	
	@Value("#{'${emailIds}'.split(',')}")
	private List<String> mailList;
	
	@PostMapping(value = "/send-Email")
	public Map<String, Object> sendSampleEmail(){
		Map<String, Object> res= new HashMap<>();
		DTO dto=new DTO();
	
		PrepareAndSendEmail.sendUsersMails(dto, mailList);
		res.put("success", true);
		return res;
	}

}
