package com.web.blog.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.FollowDao;
import com.web.blog.dto.Follow;
import com.web.blog.dto.FollowReq;

@Service
public class FollowServiceImpl implements FollowService{
	@Autowired
	FollowDao dao;

	@Override
	public void addfollow(FollowReq req) {
		dao.addfollow(req);
	}

	@Override
	public Follow getFollowById(FollowReq req) {
		return dao.getFollowById(req);
	}

	@Override
	public void unfollow(String followId) {
		dao.unfollow(followId);
	}

	@Override
	public List<HashMap<String, String>> followlist(String memberId) {
		return dao.followlist(memberId);
	}
	
	

}
