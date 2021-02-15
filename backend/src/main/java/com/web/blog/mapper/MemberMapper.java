package com.web.blog.mapper;

import java.util.HashMap;
import java.util.List;

import com.web.blog.dto.Member;
import com.web.blog.dto.Memberupdate;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;

public interface MemberMapper {
	public void signup(signupReq req);
	public void update(Memberupdate req);
	public void imgupdate(String memberId, String img);
	public Member getUserByEmail(String email);
	public Member getUserById(String memberId);
	public void withdraw(String memberId);
	public String findIdByName(String name, String phone);
	public Member login(loginReq req);
	public void emailLink(Member m);
	public void updateAuth(String memberId);
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void test(String id);
	//	public void update(String id, String email, String name, String pw, String phone, String img, String profile);
//	public void delete(String id);
//	public int login(String id, String pw);
	public String getUserByNameAndPhone(String name, String phone);
	public int updatePassword(Member target);
	public HashMap<String,String> findMemberById(String memberId);
}
