package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.VideoBoard;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.FilterService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
public class FilterController {
	@Autowired
	FilterService service;
	
	@GetMapping(value = "/filter/{keyword}")
	@ApiOperation(value = "영상게시물 검색", notes = "영상게시물 검색을 보여준다.")
	public Object search(@PathVariable String keyword) {
		List<VideoBoard> map= service.search(keyword);
		if(map!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=map;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(value = "/filter")
	@ApiOperation(value = "영상게시물 필터링", notes = "영상게시물 필터링을 보여준다.")
	public Object filter(String genre, String color) {
		List<VideoBoard> map= service.filter(genre, color);
		if(map!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=map;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}
