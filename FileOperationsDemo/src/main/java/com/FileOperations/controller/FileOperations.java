package com.FileOperations.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileOperations {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Value("classpath:sampleReadFile.txt")
	Resource resource;
	
	// File Reading from resource by implementing ResourceLoader
	@RequestMapping(value = "/read-file-by-resourceLoader", method = RequestMethod.POST)
	public void sampleReadTxtByResourceLoader() {
		System.out.println("Hello World");
		Resource resource = resourceLoader.getResource("classpath:sampleReadFile.txt");
		try {
			File file=resource.getFile();
			String content= new String(Files.readAllBytes(file.toPath()));
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//File Reading from classPathResource
	@RequestMapping(value = "/read-file-by-classPathResource", method = RequestMethod.POST)
	public void readFromClassPathResource() {
		try {
			String str="PIP_CHECKOUT_CYD8280CL";
			Resource resource=new ClassPathResource("/templates/"+str+".pdf");
			File file=resource.getFile();
			String content=new String(Files.readAllBytes(file.toPath()));
			System.out.println(content); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//File Reading from @value Annotation 
	@RequestMapping(value = "/read-file-by-value", method = RequestMethod.POST)
	public void readFromValue() {
		try {
			File file = resource.getFile();
			String content= new String(Files.readAllBytes(file.toPath()));
			System.out.println(content);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//File reading from D Drive or any specified path
	@RequestMapping(value = "/read-file-from-path", method = RequestMethod.POST)
	public void readFromSpecifiedPath() {
		try {
			Resource resource= resourceLoader.getResource("file:D:/TestDoc.txt");
			File file=resource.getFile();
			String content= new String(Files.readAllBytes(file.toPath()));
			System.out.println(content);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
