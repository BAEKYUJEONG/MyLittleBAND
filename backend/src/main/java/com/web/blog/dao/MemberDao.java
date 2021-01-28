package com.web.blog.dao;

import java.util.HashMap;

import com.web.blog.dto.Member;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;

public interface MemberDao {
	public void signup(signupReq req);
	public void update(String memberId, Member m);
	public void imgupdate(String memberId, String img);
	public Member getUserByEmail(String email);
	public void withdraw(String memberId);
	public String findIdByName(String name, String phone);
	public HashMap<String, String> login(loginReq req);
	
	
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void test(String id);
	//	public void update(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void delete(String id);
//	public int login(String id, String pw);
}

