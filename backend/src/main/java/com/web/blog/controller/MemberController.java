package com.web.blog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dto.Member;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;
import com.web.blog.service.MailService;
import com.web.blog.util.TempKey;
import com.web.blog.model.BasicResponse;
import com.web.blog.service.JwtService;
import com.web.blog.service.MemberService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
//        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
//        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
//        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })


@CrossOrigin
@RestController
public class MemberController {
	@Autowired
	MemberService service;
	@Autowired
	private JwtService jwtservice;
	
	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MailService mailService;
	
	@PostMapping(value = "/signup")
	@ApiOperation(value = "회원가입", notes = "성공 시 회원가입 완료")
	@ApiResponses({
		@ApiResponse(code= 200, message="회원가입 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object signup(@RequestBody signupReq req) {
		Member check=service.getUserByEmail(req.getEmail());
		if(check==null) {
			service.signup(req);
			final BasicResponse result = new BasicResponse();
	        result.status = true;
	        result.data = "success";
	        return new ResponseEntity<>(result, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 이메일 인증 요청
	 * @param email
	 * @return ResponseEntity<>(null, HttpStatus)
	 */
	@PostMapping(value = "/signup/validation")
	public Object signupValidate(@RequestBody String email){
		// 임의의 authkey 생성
	    String authkey = new TempKey().getKey(50, false);
	    System.out.println(email);
	    String subject = "나작밴 회원가입 승인 메일 링크";
	    StringBuilder sb = new StringBuilder();
	    sb.append("링크를 클릭하시면 이메일 인증이 완료됩니다.\n\n").append("http://i4a408.p.ssafy.io:3000/validated?email=").append(email)
	            .append("&authkey=").append(authkey);
	    Member target = service.getUserByEmail(email);
	    target.setAuthkey(authkey);
	    service.emailLink(target);
	    mailService.send(subject, sb.toString(), "anonymous@najakban.com", email);
	    
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	/**
	 * 이메일 링크 클릭 후 회원 가입 완료
	 * @param authkey
	 * @return ResponseEntity<>(null, HttpStatus)
	 */
	@PostMapping(value = "/signup/validated")
	public Object signupValidated(@RequestBody Map<String, String> info) {
		Member target = service.getUserByEmail(info.get("email"));
		BasicResponse response = new BasicResponse();
		
		// Authkey가 일치
		if(target.getAuthkey().equals(info.get("authkey"))) {
			service.updateAuth(target.getMemberId());
			response.status = true;
		} else {
			response.status = false;
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);	
	}
	
	@PutMapping(value = "/member/{memberId}")
	@ApiOperation(value = "회원정보수정")
	public Object update(@RequestBody Member m, @PathVariable String memberId) {
		System.out.println(memberId);
		
		service.update(memberId, m);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/memberimg/{memberId}")
	@ApiOperation(value = "이미지수정")
	public Object imgupdate(@RequestParam(required = true) final String img, @PathVariable String memberId) {
		service.imgupdate(memberId, img);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/member/{memberId}")
	@ApiOperation(value = "회원탈퇴")
	public Object withdraw(@PathVariable String memberId) {
		service.withdraw(memberId);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = "success";
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/member")
	@ApiOperation(value = "아이디찾기")
	public Object findIdByName(@RequestParam(required = true) final String name,
            @RequestParam(required = true) final String phone) {
		String email=service.findIdByName(name,phone);
//		System.out.println(email);
		final BasicResponse result = new BasicResponse();
        result.status = true;
        result.data = email;
        return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/login")
	@ApiOperation(value = "로그인", notes = "성공 시 로그인 완료")
	@ApiResponses({
		@ApiResponse(code= 200, message="로그인 성공"),
		@ApiResponse(code= 400, message="잘못된 접근"),
	})
	public Object login(@RequestBody loginReq req, HttpServletResponse response) {
		Member member=service.login(req);
		if(member!=null) {
			System.out.println("login");
			
			String token = jwtservice.create(member);
			logger.trace("로그인 토큰정보 : {}", token);
			final BasicResponse result = new BasicResponse();
	        response.setHeader("auth-token", token);
			result.status = true;
	        result.data = "success";
	        result.object=member;
	        return new ResponseEntity<>(result, HttpStatus.OK);
	        
		}
		else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
//	@RequestMapping(value="/signu", method=RequestMethod.)
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile) {
//		service.signup(id, email, name, pw, phone, img, profile);
//	}
	
	
	
//	
//	@PostMapping(value="/test")
//	public void test(String id) {
//		service.test(id);
////		System.out.println("con");
//	}
	
}
