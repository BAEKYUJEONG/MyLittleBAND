package com.web.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.blog.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public void signup(String id, String email, String name, String pw, String phone, String img, String profile) {
		service.signup(id, email, name, pw, phone, img, profile);
	}
	
	@PostMapping(value="/test")
	public void test(String id) {
		service.test(id);
//		System.out.println("con");
	}
	
}
