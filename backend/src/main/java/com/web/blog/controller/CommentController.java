package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Comment;
import com.web.blog.dto.OtherComment;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.CommentService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	//댓글 업로드
	@PostMapping(value = "/comment")
	@ApiOperation(value = "1", notes = "2")
	private ResponseEntity create(@RequestBody Comment comment) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			boolean res = service.insert(comment);
			System.out.println(res);
			if(res) {
				result.put("succes", "success");
				entity = new ResponseEntity(result, HttpStatus.OK);
			}
			else {
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 목록 불러오기.
	@GetMapping(value = "/comment/{boardId}")
	@ApiOperation(value = "1", notes = "2")
	private ResponseEntity selectList(@PathVariable String boardId) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<OtherComment> commentList = service.selectList(boardId);
			
			if(commentList.size() > 0) {
				result.put("succes", "success");
				result.put("object", commentList);
				entity = new ResponseEntity(result, HttpStatus.OK);
			}
			else {
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@GetMapping(value= "/count/{boardId}")
	@ApiOperation(value = "1", notes = "2")
	private Object count(@PathVariable String boardId) {
		int comment = service.count(boardId);
		if(comment!=0) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object = comment;
	        return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}


    
}
