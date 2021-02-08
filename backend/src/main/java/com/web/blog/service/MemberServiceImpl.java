package com.web.blog.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.MemberDao;
import com.web.blog.dto.Member;
import com.web.blog.dto.loginReq;
import com.web.blog.dto.signupReq;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao dao;

	@Override
	public void signup(signupReq req) {
		dao.signup(req);
	}

	@Override
	public void update(String memberId, Member m) {
		dao.update(memberId, m);
	}
	
	@Override
	public void imgupdate(String memberId, String img) {
		dao.imgupdate(memberId, img);
	}

	@Override
	public Member getUserByEmail(String email) {
		return dao.getUserByEmail(email);
	}

	@Override
	public void withdraw(String memberId) {
		dao.withdraw(memberId);
	}

	@Override
	public String findIdByName(String name, String phone) {
		return dao.findIdByName(name, phone);
	}

	@Override
	public Member login(loginReq req) {
		return dao.login(req);
	}

	@Override
	public Member getUserById(String memberId) {
		return dao.getUserById(memberId);
	}
	
	@Override
	public void emailLink(Member m) {
		dao.emailLink(m);
	}

	@Override
	public void updateAuth(String memberId) {
		dao.updateAuth(memberId);
	}

	@Override
	public String getUserByNameAndPhone(String name, String phone) {
		return dao.getUserByNameAndPhone(name, phone);
	}


//	@Override
//	public void signup(String id, String email, String name, String pw, String phone, String img, String profile) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void test(String id) {
//		dao.test(id);
//		System.out.println("ser");
//	}
	
}
