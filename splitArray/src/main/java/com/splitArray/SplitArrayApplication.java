package com.splitArray;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.splitArray.controller.HomeController;

@SpringBootApplication
public class SplitArrayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SplitArrayApplication.class, args);
		
		/*
		 * HomeController homeController=new HomeController();
		 * homeController.splitArray();
		 */
	}

}
