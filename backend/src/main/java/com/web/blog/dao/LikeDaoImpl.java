package com.web.blog.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.blog.dto.Like;
import com.web.blog.dto.LikeReq;
import com.web.blog.mapper.LikeMapper;



@Repository
public class LikeDaoImpl implements LikeDao {
	@Autowired
	LikeMapper mapper;

	@Override
	public void addlike(LikeReq req) {
		mapper.addlike(req);
	}

	@Override
	public void unlike(String likeId) {
		mapper.unlike(likeId);
	}

	@Override
	public Like getlikeById(LikeReq req) {
		return mapper.getlikeById(req);
	}

	@Override
	public List<HashMap<String, String>> likelist(String memberId) {
		return mapper.likelist(memberId);
	}

	
}
