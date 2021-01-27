package com.web.blog.dao;

import com.web.blog.dto.Member;

public interface MemberDao {
	public void signup(Member m);
	public Member getUserByEmail(String email);
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void test(String id);
	//	public void update(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void delete(String id);
//	public int login(String id, String pw);
}

