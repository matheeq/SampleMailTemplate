package com.FileOperations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.FileOperations.util.PrepareAndSendEmail;

@SpringBootApplication
public class FileOperationsDemoApplication {

	@Value("${prepareAndSendEmailIds}")
	public void setEmailIsStatic(String emailIds) {
		PrepareAndSendEmail.emailIds = emailIds;
	}

	public static void main(String[] args) {
		SpringApplication.run(FileOperationsDemoApplication.class, args);
		System.out.println("Hello World");
	}

}
