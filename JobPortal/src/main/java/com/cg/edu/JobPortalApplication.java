package com.cg.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JobPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPortalApplication.class, args);
		
	}
	

	@Bean          // to create only one instance of rest template.
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	

	
}
