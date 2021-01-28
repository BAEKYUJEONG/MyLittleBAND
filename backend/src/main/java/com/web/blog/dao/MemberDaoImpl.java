package com.web.blog.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Member;
import com.web.blog.mapper.MemberMapper;

@Repository
public class MemberDaoImpl implements MemberDao {
	@Autowired
	MemberMapper mapper;

	@Override
	public void signup(Member m) {
		// TODO Auto-generated method stub
		mapper.signup(m);
	}

	@Override
	public Member getUserByEmail(String email) {
		return mapper.getUserByEmail(email);
	
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
