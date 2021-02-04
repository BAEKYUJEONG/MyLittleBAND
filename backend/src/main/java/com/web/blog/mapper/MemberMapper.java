package com.web.blog.mapper;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Member;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;

public interface MemberMapper {
	public void signup(signupReq req);
	public void update(String memberId, Member m);
	public void imgupdate(String memberId, String img);
	public Member getUserByEmail(String email);
	public Member getUserById(String memberId);
	public void withdraw(String memberId);
	public String findIdByName(String name, String phone);
	public Member login(loginReq req);
	
}
