package com.web.blog.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Member;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;
import com.web.blog.mapper.MemberMapper;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	MemberMapper mapper;

	@Override
	public void signup(signupReq req) {
		// TODO Auto-generated method stub
		mapper.signup(req);
	}

	@Override
	public void update(String memberId, Member m) {
		mapper.update(memberId, m);
	}
	
	@Override
	public void imgupdate(String memberId, String img) {
		mapper.imgupdate(memberId, img);
	}

	@Override
	public Member getUserByEmail(String email) {
		return mapper.getUserByEmail(email);
	
	}

	@Override
	public void withdraw(String memberId) {
		mapper.withdraw(memberId);
	}

	@Override
	public String findIdByName(String name, String phone) {
		return mapper.findIdByName(name, phone);
		
	}

	@Override
	public Member login(loginReq req) {
		return mapper.login(req);
	}

	@Override
	public Member getUserById(String memberId) {
		return mapper.getUserById(memberId);
	}

	public void emailLink(Member m) {
		mapper.emailLink(m);
	}

	@Override
	public void updateAuth(String memberId) {
		mapper.updateAuth(memberId);
	}

	@Override
	public String getUserByNameAndPhone(String name, String phone) {
		return mapper.getUserByNameAndPhone(name, phone);
	}
	
//	@Override
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile) {
//		mapper.signup(id, email, name, pw, phone, img, profile);
//	}
//	
//	@Override
//	public void test(String id) {
//		mapper.test(id);
//		System.out.println("dao");
//	}

//	@Override
//	public void update(String id, String email, String name, String pw, String phone, String img, String profile) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(String id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public int login(String id, String pw) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	
}
