package com.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class JwtSecurityApplication {

	@GetMapping("/")
    String home() {
        return "Hello, JWT!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}

}
