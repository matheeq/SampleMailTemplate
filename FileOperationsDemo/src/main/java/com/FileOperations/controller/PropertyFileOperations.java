package com.FileOperations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyFileOperations {
	
	@Autowired
	private Environment environment;
	
	@Value("${database}")
	private String dB;
	

	
	//Reading data from Properties file
	@RequestMapping(value ="/read-property-file", method = RequestMethod.POST )
	public void data() {
		System.out.println("DataBase:"+ environment.getProperty("database"));
		System.out.println("HostName:"+ environment.getProperty("hostname"));
		System.out.println("HostName:"+ environment.getProperty("configuration"));
		System.out.println("DB:"+ dB);
		
	}

}
