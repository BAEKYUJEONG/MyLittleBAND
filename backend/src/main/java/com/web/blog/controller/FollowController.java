package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	@ApiOperation(value = "팔로우하기", notes = "팔로우 요청을 보낸다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="팔로우 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object addfollow(@RequestBody FollowReq req) {
		service.addfollow(req);
		service.followup(req.getBandId());
		HashMap<String, String> follow=service.followcheck(req.getMemberId(), req.getBandId());
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "follow success";
        result.object=follow;
        return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/follow/followlist/{followId}")
	@ApiOperation(value = "언팔로우하기", notes = "언팔로우 요청을 보낸다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="언팔로우 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object unfollow(@PathVariable String followId) {
		service.followdown(followId);
		service.unfollow(followId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "unfollow success";
        return new ResponseEntity<>(result, HttpStatus.OK);
		
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
	
	@GetMapping(value = "/followcheck")
	@ApiOperation(value = "팔로우체크", notes = "팔로우체크를 보여준다.")
	public Object followcheck(@RequestParam String memberId, @RequestParam String bandId) {
		HashMap<String, String> follow= service.followcheck(memberId,bandId);
		System.out.println(follow);
		if(follow!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=follow;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/follownum/{bandId}")
	@ApiOperation(value = "팔로워 수 체크", notes = "팔로워 수 보여준다.")
	public Object follownum(@PathVariable String bandId) {
		List<HashMap<String, String>> follownum= service.follownum(bandId);
		if(follownum!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=follownum;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
}
