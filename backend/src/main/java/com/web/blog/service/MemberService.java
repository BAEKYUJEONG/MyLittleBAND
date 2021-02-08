package com.web.blog.service;

import java.util.HashMap;

import com.web.blog.dto.Member;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;

public interface MemberService {
	public void signup(signupReq req);
	public void update(String memberId, Member m);
	public void imgupdate(String memberId, String img);
	public Member getUserByEmail(String email);
	public Member getUserById(String memberId);
	public void withdraw(String memberId);
	public String findIdByName(String name, String phone);
	public Member login(loginReq req);
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void test(String id);
	public void emailLink(Member m);		// 이메일 인증 키 삽입
	public void updateAuth(String memberId);
	public String getUserByNameAndPhone(String name, String phone);
	public int updatePassword(Member target);
}
