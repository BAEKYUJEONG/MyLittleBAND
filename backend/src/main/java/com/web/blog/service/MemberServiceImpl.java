package com.web.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	MemberDao dao;
	
	@Override
	public void signup(String id, String email, String name, String pw, String phone, String img, String profile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void test(String id) {
		dao.test(id);
		System.out.println("ser");
	}
	
}
