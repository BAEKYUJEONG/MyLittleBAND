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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Band;
import com.web.blog.dto.BandInvite;
import com.web.blog.dto.BandOpened;
import com.web.blog.dto.Crew;
import com.web.blog.dto.Member;
import com.web.blog.dto.VideoBoard;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.BandService;
import com.web.blog.service.MemberService;
import com.web.blog.service.VideoBoardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin
@RestController
public class BandController {
	@Autowired
	BandService service;
	@Autowired
	MemberService mservice;
	@Autowired
	VideoBoardService videoboardservice;
	
	@PostMapping(value = "/band/{memberId}")
	@ApiOperation(value = "밴드개설", notes = "밴드개설 요청을 보낸다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="밴드개설 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object opened(@RequestBody BandOpened req,@PathVariable String memberId) {
		Band check=service.getBandByName(req.getName());
//		System.out.println(check);
		if(check==null) {
//			Member m= mservice.getUserById(memberId);
//			System.out.println(m.getEmail());
//			System.out.println("들어옴");
			videoboardservice.settime();
			service.opened(req);
			Band b= service.getBandByName(req.getName());
//			System.out.println(memberId);
//			System.out.println(b.getBandId());
			service.addcrew(memberId, b.getBandId());
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/band/{bandId}")
	@ApiOperation(value = "밴드정보", notes = "밴드정보를 보여준다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="밴드정보 가져오기 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object bandinfo(@PathVariable String bandId) {
		HashMap<String, String> map= service.bandinfo(bandId);
		System.out.println(map);
		if(map!=null) {
			System.out.println("bandinfo");
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
	
	@GetMapping(value = "/band/member/{bandId}")
	@ApiOperation(value = "밴드 멤버정보", notes = "밴드 멤버정보를 보여준다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="밴드 멤버정보 가져오기 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object bandmember(@PathVariable String bandId) {
		List<HashMap<String, String>> map= service.bandmember(bandId);
		System.out.println(map);
		if(map!=null) {
			System.out.println("bandmember");
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
	
	@PutMapping(value = "/band/member/chief/{crewId}")
	@ApiOperation(value = "밴드장 위임", notes = "밴드장 위임 요청을 보낸다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="밴드장 위임 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object mandatechief(@PathVariable String crewId,String mandatecrewId) {
		Crew c= service.getCrewById(crewId);
		Crew m= service.getCrewById(mandatecrewId);
		if((c.isIschief()==true && m.isIschief()==false) && (c.getBandid().equals(m.getBandid()))) {
			System.out.println("mandate");
			service.mandatechief(c.getCrewId());
			service.receivechief(m.getCrewId());
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
	      
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/band/member/{crewId}")
	@ApiOperation(value = "밴드멤버 탈퇴", notes = "밴드멤버 탈퇴를 요청한다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="밴드멤버 탈퇴 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object withdrawband(@PathVariable String crewId) {
		
		System.out.println("withdrawband");
		service.withdrawband(crewId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/band/member/{crewId}")
	@ApiOperation(value = "멤버세션 수정", notes = "멤버세션 수정요청을 보낸다.")
	@ApiResponses({
		@ApiResponse(code= 200, message="멤버세션 수정 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object changesession(@PathVariable String crewId, String session) {
		System.out.println("changesession");
		service.changesession(crewId, session);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	      
	}
	
	@GetMapping(value = "/band-list/{memberId}")
	@ApiOperation(value = "멤버의 밴드정보", notes = "멤버의 밴드정보를 보여준다.")
	
	public Object memberofband(@PathVariable String memberId) {
		List<HashMap<String, String>> map= service.memberofband(memberId);
		System.out.println(map);
		if(map!=null) {
			System.out.println("bandmember");
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
	
	@PutMapping(value = "/band/{bandId}")
	@ApiOperation(value = "밴드정보 수정", notes = "밴드정보 수정요청을 보낸다.")
	public Object changeinfo(@PathVariable String bandId, String name, String profile) {
		service.changeinfo(bandId, name, profile);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	      
	}
	
	@DeleteMapping(value = "/band/{bandId}")
	@ApiOperation(value = "밴드 해체", notes = "밴드 해체를 요청한다.")
	public Object delband(@PathVariable String bandId) {
		service.delcrew(bandId);
		service.delfollow(bandId);
		service.delcalendar(bandId);
		service.delband(bandId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/band/img/{bandId}")
	@ApiOperation(value = "이미지수정")
	public Object imgupdate(@RequestParam(required = true) final String img, @PathVariable String bandId) {
		service.imgupdate(bandId, img);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/videoboard/videolist/{bandId}")
	@ApiOperation(value = "밴드의 영상게시물 목록", notes = "밴드의 영상게시물 목록을 보여준다.")
	
	public Object videoofband(@PathVariable String bandId) {
		List<VideoBoard> list= service.videoofband(bandId);
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
	
	@GetMapping(value = "/band/find/{keyword}")
	@ApiOperation(value = "밴드이름으로 검색", notes = "밴드이름으로 검색 목록을 보여준다.")
	
	public Object searchband(@PathVariable String keyword) {
		List<HashMap<String, String>> list= service.searchband(keyword);
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
	
	@PostMapping(value = "/band/invite")
	@ApiOperation(value = "밴드초대", notes = "밴드초대 요청을 보낸다.")
	public Object bandinvite(@RequestBody BandInvite req) {
		
		service.bandinvite(req);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
