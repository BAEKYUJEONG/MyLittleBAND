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

import com.web.blog.dto.Calendar;
import com.web.blog.dto.Like;
import com.web.blog.dto.LikeReq;
import com.web.blog.dto.Member;
import com.web.blog.dto.signupReq;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.CalendarService;
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
public class CalendarController {
	@Autowired
	CalendarService service;
	
	@PostMapping(value = "/calendar/{bandId}/{month}")
	@ApiOperation(value = "밴드캘린더", notes = "성공 시 밴드캘린더 받기 완료")
	@ApiResponses({
		@ApiResponse(code= 200, message="밴드캘린더 요청 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object month(@PathVariable String bandId, @PathVariable String month) {
		List<Calendar> list=service.month(bandId, month);
		if(list!=null) {
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        result.object=list;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	        
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	
	
}
