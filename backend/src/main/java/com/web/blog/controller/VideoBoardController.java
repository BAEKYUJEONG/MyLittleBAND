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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.VideoBoard;
import com.web.blog.dto.VideoBoardReq;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.VideoBoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin
@RestController
public class VideoBoardController {
	@Autowired
	VideoBoardService service;
	
	@PostMapping(value = "/videoboard/{bandId}")
	@ApiOperation(value = "영상게시글 작성", notes = "영상게시글 작성 요청을 보낸다.")
	public Object writevideo(@RequestBody VideoBoardReq req,@PathVariable String bandId) {
		
		req.setBandId(bandId);
		service.settime();
		service.writevideo(req);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/videoboard/list")
	@ApiOperation(value = "영상게시글 리스트", notes = "영상게시글 리스트를 보여준다.")
	public Object videoboardlist() {
		List<HashMap<String, String>> map= service.videoboardlist();
		
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
	
	@GetMapping(value = "/videoboard/list/{boardId}")
	@ApiOperation(value = "영상게시글 읽기", notes = "영상게시글 내용을 보여준다.")
	public Object readvideo(@PathVariable String boardId) {
		VideoBoard map= service.readvideo(boardId);
		if(map!=null) {
			service.countup(boardId);
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
	
	@DeleteMapping(value = "/videoboard/{boardId}")
	@ApiOperation(value = "영상게시글 삭제", notes = "영상게시글 삭제를 요청한다.")
	public Object delvideo(@PathVariable String boardId) {
		service.dellike(boardId);
		service.delvideo(boardId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/videoboard/{boardId}")
	@ApiOperation(value = "영상게시글 수정", notes = "영상게시글 수정요청을 보낸다.")
	public Object changevideo(@PathVariable String boardId, String boardSubject, String boardContent) {
		service.changevideo(boardId, boardSubject, boardContent);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	      
	}
	
//	@GetMapping(value = "/videoboard/ranking/view")
//	@ApiOperation(value = "영상게시글 랭킹 읽기", notes = "영상게시글 랭킹을 보여준다.")
//	public Object rankingvideo() {
//		List<HashMap<String, String>> map
////		if(map!=null) {
////			service.countup(boardId);
////			final BasicResponse result = new BasicResponse();
////	        result.status = true;
////	        result.data = "success";
////	        result.object=map;
////	        return new ResponseEntity<>(result, HttpStatus.OK);
////	      
////		}
////		else {
////			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
////		}
//		
//	}
}
