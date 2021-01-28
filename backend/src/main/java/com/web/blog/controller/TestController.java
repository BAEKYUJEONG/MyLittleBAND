package com.web.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@GetMapping(value = "/addition")
	public Integer addition(Integer num1, Integer num2) {
		return num1 + num2;
	}

	@PostMapping(value = "/subtraction")
	public Integer subtraction(Integer num1, Integer num2) {
		return num1 - num2;
	}

	@PutMapping(value = "/multiplication")
	public Integer multiplication(Integer num1, Integer num2) {
		return num1 * num2;
	}

	@DeleteMapping(value = "/division")
	public Integer division(Integer num1, Integer num2) {
		return num1 / num2;
	}
}
