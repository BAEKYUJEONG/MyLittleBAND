package com.web.blog.service;


import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.blog.dao.LikeDao;
import com.web.blog.dto.Like;
import com.web.blog.dto.LikeReq;


@Service
public class LikeServiceImpl implements LikeService{
	@Autowired
	LikeDao dao;

	@Override
	public void addlike(LikeReq req) {
		dao.addlike(req);
	}

	@Override
	public void unlike(String likeId) {
		dao.unlike(likeId);
	}

	@Override
	public Like getlikeById(LikeReq req) {
		return dao.getlikeById(req);
	}

	@Override
	public List<HashMap<String, String>> likelist(String memberId) {
		return dao.likelist(memberId);
	}


}
