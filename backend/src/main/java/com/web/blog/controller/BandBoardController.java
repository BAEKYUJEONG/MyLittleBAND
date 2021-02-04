package com.web.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Band;
import com.web.blog.dto.BandOpened;
import com.web.blog.dto.Member;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.BandService;
import com.web.blog.service.MemberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin
@RestController
public class BandBoardController {
//	@Autowired
//	BandService service;
//	@Autowired
//	MemberService mservice;
//	
//	@PostMapping(value = "/band/{memberId}")
//	@ApiOperation(value = "밴드개설", notes = "밴드개설 요청을 보낸다.")
//	@ApiResponses({
//		@ApiResponse(code= 200, message="밴드개설 성공"),
//		@ApiResponse(code= 400, message="잘못된 접근"),
//	})
//	public Object opened(@RequestBody BandOpened req,@PathVariable String memberId) {
//		Band check=service.getBandByName(req.getName());
////		System.out.println(check);
//		if(check==null) {
//			mservice.getUserById(memberId);
//			System.out.println("들어옴");
//			service.opened(req);
//			Band b= service.getBandByName(req.getName());
////			System.out.println(memberId);
////			System.out.println(b.getBandId());
//			service.addcrew(memberId, b.getBandId());
//			final BasicResponse result = new BasicResponse();
//	        result.status = true;
//	        result.data = "success";
//	        return new ResponseEntity<>(result, HttpStatus.OK);
//	      
//		}
//		else {
//			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//	}
	
}
