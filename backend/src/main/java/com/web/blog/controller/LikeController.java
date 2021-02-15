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

import com.web.blog.dto.FollowReq;
import com.web.blog.dto.Like;
import com.web.blog.dto.LikeReq;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.LikeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin
@RestController
public class LikeController {
	@Autowired
	LikeService service;
	
	@PostMapping(value = "/like")
	@ApiOperation(value = "좋아요하기", notes = "좋아요 요청을 보낸다.")
	public Object addfollow(@RequestBody LikeReq req) {
		service.addlike(req);
		service.likeup(req.getBoardId());
		HashMap<String, String> like=service.likecheck(req.getMemberId(), req.getBoardId());
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "like success";
        result.object=like;
        return new ResponseEntity<>(result, HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/like/likelist/{likeId}")
	@ApiOperation(value = "라이크 취소", notes = "라이크 취소요청을 보낸다.")
	public Object unlike(@PathVariable String likeId) {
		service.likedown(likeId);
		service.unlike(likeId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "unlike success";
        return new ResponseEntity<>(result, HttpStatus.OK);
		
	}

	@GetMapping(value = "/likelist/{memberId}")
	@ApiOperation(value = "라이크목록", notes = "라이크목록을 보여준다.")
	public Object likelist(@PathVariable String memberId) {
		List<HashMap<String, String>> map= service.likelist(memberId);
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
	
	
	@GetMapping(value = "/likecheck")
	@ApiOperation(value = "라이크체크", notes = "라이크체크를 보여준다.")
	public Object likecheck(@RequestParam String memberId, @RequestParam String boardId) {
		HashMap<String, String> like= service.likecheck(memberId,boardId);
		if(like!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=like;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	
}
