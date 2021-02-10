package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Follow;
import com.web.blog.dto.FollowReq;
import com.web.blog.mapper.FollowMapper;


@Repository
public class FollowDaoImpl implements FollowDao {
	@Autowired
	FollowMapper mapper;

	@Override
	public void addfollow(FollowReq req) {
		mapper.addfollow(req);
	}

	@Override
	public Follow getFollowById(FollowReq req) {
		return mapper.getFollowById(req);
	}

	@Override
	public void unfollow(String followId) {
		mapper.unfollow(followId);
	}

	@Override
	public List<HashMap<String, String>> followlist(String memberId) {
		return mapper.followlist(memberId);
	}

	@Override
	public HashMap<String, String> followcheck(String memberId, String bandId) {
		return mapper.followcheck(memberId, bandId);
	}

	

	
	
	
}
