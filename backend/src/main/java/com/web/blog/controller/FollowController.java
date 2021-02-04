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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Follow;
import com.web.blog.dto.FollowReq;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.FollowService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin
@RestController
public class FollowController {
	@Autowired
	FollowService service;
	
	@PostMapping(value = "/follow")
	@ApiOperation(value = "팔로우하기(취소)", notes = "팔로우(취소) 요청을 보낸다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="팔로우(취소) 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object follow(@RequestBody FollowReq req) {
		Follow check= service.getFollowById(req);
		if(check==null) {
			System.out.println("follow");
			service.addfollow(req);
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "follow success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			System.out.println("unfollow");
			service.unfollow(check.getFollowId());
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "unfollow success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/followlist/{memberId}")
	@ApiOperation(value = "팔로우목록", notes = "팔로우목록을 보여준다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="팔로우목록 가져오기 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object followlist(@PathVariable String memberId) {
		List<HashMap<String, String>> map= service.followlist(memberId);
		System.out.println(map);
		if(map!=null) {
			System.out.println("list");
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
