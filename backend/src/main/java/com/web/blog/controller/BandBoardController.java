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

import com.web.blog.dto.BandBoard;
import com.web.blog.dto.BandBoardReq;
import com.web.blog.dto.VideoBoard;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.BandBoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class BandBoardController {
	@Autowired
	BandBoardService service;
	
	@PostMapping(value = "/bandboard/{bandId}")
	@ApiOperation(value = "밴드게시글 작성", notes = "밴드게시글 작성 요청을 보낸다.")
	public Object writebandboard(@RequestBody BandBoardReq req,@PathVariable String bandId) {
		
		req.setBandId(bandId);
		service.settime();
		service.writebandboard(req);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/bandboard/list")
	@ApiOperation(value = "밴드게시글 리스트", notes = "밴드게시글 리스트를 보여준다.")
	public Object bandboardlist() {
		List<BandBoard> map= service.bandboardlist();
		
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
	
	@GetMapping(value = "/bandboard/list/{bandboardId}")
	@ApiOperation(value = "밴드게시글 읽기", notes = "밴드게시글 내용을 보여준다.")
	public Object readbandboard(@PathVariable String bandboardId) {
		BandBoard map= service.readbandboard(bandboardId);
		if(map!=null) {
			service.countup(bandboardId);
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
	
	@DeleteMapping(value = "/bandboard/{bandboardId}")
	@ApiOperation(value = "밴드게시글 삭제", notes = "밴드게시글 삭제를 요청한다.")
	public Object delbandboard(@PathVariable String bandboardId) {
		service.delbandboard(bandboardId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/bandboard/{bandboardId}")
	@ApiOperation(value = "밴드게시글 수정", notes = "밴드게시글 수정요청을 보낸다.")
	public Object changevideo(@PathVariable String bandboardId, String title, String content) {
		service.changebandboard(bandboardId, title, content);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	      
	}
}
