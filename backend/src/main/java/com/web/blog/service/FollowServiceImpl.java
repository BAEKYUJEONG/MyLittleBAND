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

	@Override
	public HashMap<String, String> followcheck(String memberId, String bandId) {
		return dao.followcheck(memberId, bandId);
	}

	@Override
	public List<HashMap<String, String>> follownum(String bandId) {
		return dao.follownum(bandId);
	}

	@Override
	public void followup(String bandId) {
		dao.followup(bandId);
	}

	@Override
	public void followdown(String followId) {
		System.out.println("?");
		dao.followdown(followId);
	}
	
	

}
