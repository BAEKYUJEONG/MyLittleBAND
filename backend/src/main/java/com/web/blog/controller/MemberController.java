package com.web.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Member;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.MemberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin
@RestController
public class MemberController {
	@Autowired
	MemberService service;
	
	@PostMapping(value = "/signup")
	@ApiOperation(value = "회원가입")
	public Object signup(@RequestBody Member m) {
		Member check=service.getUserByEmail(m.getEmail());
		if(check==null) {
			service.signup(m);
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
	        
		}
		else {
			System.out.println("double");
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
	}
	
//	@RequestMapping(value="/signu", method=RequestMethod.)
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile) {
//		service.signup(id, email, name, pw, phone, img, profile);
//	}
	
	
	
//	
//	@PostMapping(value="/test")
//	public void test(String id) {
//		service.test(id);
////		System.out.println("con");
//	}
	
}
