package com.jwt.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


	@GetMapping("/jwt")
	public String homne(Principal principal) {
		return "Hello, " + principal.getName();
	}
	
}
