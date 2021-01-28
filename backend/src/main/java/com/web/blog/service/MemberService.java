package com.web.blog.service;

import com.web.blog.dto.Member;

public interface MemberService {
	public void signup(Member m);
	public Member getUserByEmail(String email);
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void test(String id);
	
}
