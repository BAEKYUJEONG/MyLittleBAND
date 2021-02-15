package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.BandComment;
import com.web.blog.dto.OtherComment;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.BandCommentService;

@CrossOrigin
@RestController
public class BandCommentController {

	@Autowired
	private BandCommentService service;

	// 댓글 목록 불러오기.
	@GetMapping(value = "/bandcomment/{bandboardId}")
	private ResponseEntity selectList(@PathVariable String bandboardId) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<OtherComment> commentList = service.selectList(bandboardId);

			if (commentList.size() > 0) {
				result.put("status", true); 
				result.put("succes", "success");    
				result.put("object", commentList);
				entity = new ResponseEntity(result, HttpStatus.OK);
			} else {
				result.put("status", false); 
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false); 
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	// 댓글 작성
	@PostMapping(value = "/bandcomment/{bandboardId}")
	private ResponseEntity create(@PathVariable String bandboardId,@RequestBody BandComment comment) {
		comment.setbandboardId(bandboardId);
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			boolean res = service.insert(comment);
			System.out.println(res);
			if (res) {
				result.put("status", true);
				result.put("succes", "success");
				entity = new ResponseEntity(result, HttpStatus.OK);
			} else {
				result.put("status", false); 
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false); 
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//댓글 수정
	@PutMapping(value = "/bandcomment/{commentId}")
	private ResponseEntity modify(@PathVariable String commentId, String content) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			boolean res = service.modify(commentId,content);
			System.out.println(res);
			if (res) {
				result.put("status", true);
				result.put("succes", "success");
				entity = new ResponseEntity(result, HttpStatus.OK);
			} else {
				result.put("status", false); 
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false); 
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	//댓글 삭제
	@DeleteMapping(value = "/bandcomment/{commentId}")
	private ResponseEntity delete(@PathVariable String commentId) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			boolean res = service.delete(commentId);
			System.out.println(res);
			if (res) {
				result.put("status", true);
				result.put("succes", "success");
				entity = new ResponseEntity(result, HttpStatus.OK);
			} else {
				result.put("status", false); 
				result.put("success", "fail");
				entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", false); 
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	// 댓글 수 불러오기
	@GetMapping(value = "/bandcount/{bandboardId}")
	private Object count(@PathVariable String bandboardId) {
		int comment = service.count(bandboardId);
		if (comment != 0) {
			final BasicResponse result = new BasicResponse();
			result.status = true;
			result.data = "success";
			result.object = comment;
			return new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
