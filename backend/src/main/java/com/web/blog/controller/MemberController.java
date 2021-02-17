package com.web.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.web.blog.dto.Memberupdate;
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
		@ApiResponse(code= 409, message="이미 가입된 회원"),
	})
	public Object signup(@RequestBody signupReq req) {
		Member check=service.getUserByEmail(req.getEmail());
		final BasicResponse result = new BasicResponse();
		HttpStatus code = null;
		
		if(check==null) {
			service.signup(req);
	        result.status = true;
	        result.data = "success";
	        code = HttpStatus.OK;
		}
		else {	
	        result.status = false;
	        result.data = "exist email";
			code = HttpStatus.CONFLICT;
		}
		
		return new ResponseEntity<>(result, code);
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
	    sb.append("링크를 클릭하시면 이메일 인증이 완료됩니다.\n\n").append("http://i4a408.p.ssafy.io/validated?email=").append(email)
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
	
	/**
	 * 회원 이메일 찾기
	 * @param info
	 * @return ResponseEntity<>(BasicResponse, HttpStatus)
	 */
	@PostMapping(value = "/member/id")
	@ApiOperation(value = "이메일 찾기", notes = "사용자 이름, 전화번호를 기반으로 이메일을 찾는다")
	public Object findEmail(@RequestBody Map<String, String> info) {
		String target = service.getUserByNameAndPhone(info.get("name"), info.get("phone"));
		BasicResponse response = new BasicResponse();
		
		if(target != null) {
			response.status = true;
			response.data = target;
		} else {
			response.status = false;
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * 회원 비밀번호 찾기
	 * @param info
	 * @return ResponseEntity<>(BasicResponse, HttpStatus)
	 */
	@PostMapping(value = "/member/pw")
	@ApiOperation(value = "임시 비밀번호 발급", notes = "사용자  이메일, 이름, 전화번호를 기반으로 임시 패스워드를 이메일로 발급받음")
	public Object findPassword(@RequestBody Map<String, String> info) {
		Member target = service.getUserByEmail(info.get("email"));
		BasicResponse response = new BasicResponse();
		
		if(target != null && target.getName().equals(info.get("name")) && target.getPhone().equals(info.get("phone"))) {
			// 임시 비밀번호 생성
			StringBuffer buffer = new StringBuffer();
			Random random = new Random();		
			String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,0,1,2,3,4,5,6,7,8,9".split(",");

			for (int i = 0; i < 10; i++) {
				buffer.append(chars[random.nextInt(chars.length)]);
			}

			target.setPw(buffer.toString());
			if(service.updatePassword(target) > 0) {
				// 메일 발송
				String subject = "나작밴 임시 비밀번호 발급";
			    StringBuilder sb = new StringBuilder();
			    sb.append("임시 비밀번호 발급입니다\n\n").append("임시 비밀번호 : ").append(target.getPw());
			    
			    mailService.send(subject, sb.toString(), "anonymous@najakban.com", target.getEmail());
			    
			    response.status = true;
			}
		} else {
			response.status = false;
		}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping(value = "/member/{memberId}")
	@ApiOperation(value = "회원정보수정")
	public Object update(@RequestBody Memberupdate req, @PathVariable String memberId) {
		System.out.println(memberId);
		req.setMemberId(memberId);
		service.update(req);
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
	
	@GetMapping(value = "/member/{memberId}")
	@ApiOperation(value = "멤버정보 가져오기")
	public Object findMemberById(@PathVariable String memberId) {
		HashMap<String, String> map= service.findMemberById(memberId);
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
	
	
	
}
