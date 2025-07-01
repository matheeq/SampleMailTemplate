package com.specification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpecificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpecificationApplication.class, args);
	}

}
